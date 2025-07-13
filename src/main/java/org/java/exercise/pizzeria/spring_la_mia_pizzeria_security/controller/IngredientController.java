package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.controller;

import java.util.List;

import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.Ingredient;
import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.Pizza;
import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    // CRUD
    // INFO: CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        repository.save(formIngredient);
        return "redirect:/ingredients";
    }

    // INFO: READ
    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = repository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Ingredient ingredient = repository.findById(id).get();
        model.addAttribute("ingredient", ingredient);
        return "ingredients/show";
    }

    // INFO: UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("ingredient", repository.findById(id).get());
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/ingredients/create-or-edit";
        }

        repository.save(formIngredient);
        return "redirect:/ingredients";
    }

    // INFO: DELETE
    @PostMapping("/delete/{id}")

    public String delete(@PathVariable("id") int id, Model model) {
        Ingredient ingredient = repository.findById(id).get();
        for (Pizza linkedPizza : ingredient.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredient);
        }

        repository.delete(ingredient);
        return "redirect:/ingredients";
    }
}