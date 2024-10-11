package springAppForProject1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import springAppForProject1.DAO.BookDAO;
import springAppForProject1.models.Book;

@Controller
@RequestMapping("/book")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("")
    public String readAll(Model model) {
        model.addAttribute("books", bookDAO.readAll());
        return "index";
    }
}
