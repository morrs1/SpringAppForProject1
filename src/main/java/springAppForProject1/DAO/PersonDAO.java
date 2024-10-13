package springAppForProject1.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springAppForProject1.models.Book;
import springAppForProject1.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> readAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person read(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE person_id=?",
                preparedStatement -> preparedStatement.setInt(1, id),
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findAny().orElse(null);
    }

    public void create(Person person) {
        jdbcTemplate.update(
                "INSERT INTO person(name, surname, year_of_birth)  VALUES(?,?,?)",
                person.getName(),
                person.getSurname(),
                person.getYearOfBirth()
        );
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(
                "UPDATE person SET name = ?, surname = ?, year_of_birth = ? WHERE person_id = ?",
                person.getName(),
                person.getSurname(),
                person.getYearOfBirth(),
                id
        );
    }
}
