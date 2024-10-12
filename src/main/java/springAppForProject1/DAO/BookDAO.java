package springAppForProject1.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springAppForProject1.models.Book;
import springAppForProject1.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> readAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book read(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE book_id=?",
                new BeanPropertyRowMapper<>(Book.class),
                new Object[]{id}
        ).stream().findAny().orElse(null);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update(
                "UPDATE book SET name=?, author=?, year=? WHERE book_id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                id
        );
    }

    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book (name, author, year) VALUES(?,?,?)",
                book.getName(),
                book.getAuthor(),
                book.getYear()
        );
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?", id);
    }

    public void assign(int bookId, int personId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", personId, bookId);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }


    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.person_id " +
                                "WHERE Book.book_id = ?", preparedStatement -> preparedStatement.setInt(1, id),
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }


}
