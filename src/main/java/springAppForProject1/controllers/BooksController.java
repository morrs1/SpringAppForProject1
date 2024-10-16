package springAppForProject1.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springAppForProject1.DAO.BookDAO;
import springAppForProject1.DAO.PersonDAO;
import springAppForProject1.models.Book;
import springAppForProject1.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;


    @GetMapping("")
    public String readAll(Model model) {
        model.addAttribute("books", bookDAO.readAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.read(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else
            model.addAttribute("people", personDAO.readAll());

        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.read(id));
        return "books/edit";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping("")
    public String create(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.create(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult,
            @PathVariable("id") int id
    ) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.assign(id, person.getPersonId());
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }


}
