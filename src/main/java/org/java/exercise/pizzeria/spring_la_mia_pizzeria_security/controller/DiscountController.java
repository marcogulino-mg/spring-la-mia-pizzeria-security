package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.controller;

import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.Discount;
import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountRepository repository;

    // INFO: CREATE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("discount") Discount formDiscount,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "discounts/create-or-edit";
        }

        repository.save(formDiscount);
        return "redirect:/pizzas/" + formDiscount.getPizza().getId();
    }

    // INFO: UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("discount", repository.findById(id).get());
        model.addAttribute("edit", true);
        return "discounts/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("discount") Discount formDiscount,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "discounts/create-or-edit";
        }

        repository.save(formDiscount);
        return "redirect:/pizzas/" + formDiscount.getPizza().getId();
    }

    // INFO: DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        Discount discount = repository.findById(id).get();
        repository.deleteById(id);
        return "redirect:/pizzas/" + discount.getPizza().getId();
    }

}
