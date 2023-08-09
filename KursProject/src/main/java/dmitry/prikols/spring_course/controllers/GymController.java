package dmitry.prikols.spring_course.controllers;


import dmitry.prikols.spring_course.dao.GymDAO;
import dmitry.prikols.spring_course.dao.PersonDAO;

import dmitry.prikols.spring_course.models.Gym;
import dmitry.prikols.spring_course.models.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/gym")
public class GymController {
    private final GymDAO gymDAO;
    private final PersonDAO personDAO;

    public GymController(GymDAO gymDAO, PersonDAO personDAO) {
        this.gymDAO = gymDAO;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("gym", gymDAO.index());
        return "gym/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("gym", gymDAO.show(id));

        Optional<Person> gymOwner = gymDAO.getGymOwner(id);

        if (gymOwner.isPresent())
            model.addAttribute("owner", gymOwner.get());
        else
            model.addAttribute("people", personDAO.index());

        return "gym/show";
    }

    @GetMapping("/new")
    public String newGym(@ModelAttribute("gym") Gym gym) {
        return "gym/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("gym") @Valid Gym gym,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "gym/new";

        gymDAO.save(gym);
        return "redirect:/gym";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("gym", gymDAO.show(id));
        return "gym/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("gym") @Valid Gym gym, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "gym/edit";

        gymDAO.update(id, gym);
        return "redirect:/gym";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        gymDAO.delete(id);
        return "redirect:/gym";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        gymDAO.release(id);
        return "redirect:/gym/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        gymDAO.assign(id, selectedPerson);
        return "redirect:/gym/" + id;
    }

}
