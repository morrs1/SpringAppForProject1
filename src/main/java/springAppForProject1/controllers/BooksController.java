package springAppForProject1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springAppForProject1.DAO.BookDAO;
import springAppForProject1.models.Book;
import springAppForProject1.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("")
    public String readAll(Model model) {
        model.addAttribute("books", bookDAO.readAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") int id, Model model, @ModelAttribute("book") Book book) {
        model.addAttribute("book", bookDAO.read(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        bookOwner.ifPresent(person -> model.addAttribute("owner", person));
//        else
//            model.addAttribute("people", personDAO.index());

        return "books/show";
    }
}
