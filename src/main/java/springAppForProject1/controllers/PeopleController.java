package springAppForProject1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springAppForProject1.DAO.PersonDAO;

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
}
