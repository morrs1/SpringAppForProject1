package springAppForProject1.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springAppForProject1.DAO.PersonDAO;
import springAppForProject1.models.Person;

@Controller
@RequestMapping("/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PeopleController {
    private final PersonDAO personDAO;

    @GetMapping("")
    public String readAll(Model model) {
        model.addAttribute("people", personDAO.readAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.read(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute Person person) {
        return "people/new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";
        personDAO.create(person);
        return "redirect:/people";
    }
}
