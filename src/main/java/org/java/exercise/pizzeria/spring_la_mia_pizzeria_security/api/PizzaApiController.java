package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.api;

import java.util.List;
import java.util.Optional;

import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.Pizza;
import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaApiController {

    @Autowired
    private PizzaService pizzaService;

    // INFO: READ
    @GetMapping
    public List<Pizza> index() {
        List<Pizza> pizzas = pizzaService.findAll();
        return pizzas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable("id") int id) {
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);

        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);
    }

    // INFO: CREATE
    @PostMapping
    public ResponseEntity<Pizza> store(@Valid @RequestBody Pizza pizza) {
        pizzaService.create(pizza);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }

    // INFO: UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@Valid @RequestBody Pizza pizza, @PathVariable int id) {
        if (pizzaService.existsById(id)) {
            pizza.setId(id);
            pizzaService.update(pizza);
            return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
        }

        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);

    }

    // INFO: DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@Valid @PathVariable("id") int id) {
        if (pizzaService.existsById(id)) {
            pizzaService.deleteById(id);
            return new ResponseEntity<Pizza>(HttpStatus.OK);
        }

        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }

}
