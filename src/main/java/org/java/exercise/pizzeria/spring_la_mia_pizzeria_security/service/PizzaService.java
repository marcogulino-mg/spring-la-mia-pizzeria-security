package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.service;

import java.util.List;
import java.util.Optional;

import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.Pizza;
import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public Boolean existsById(int id) {
        return pizzaRepository.existsById(id);
    }

    public Boolean exists(Pizza pizza) {
        return pizzaRepository.existsById(pizza.getId());
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> findByName(String query) {
        return pizzaRepository.findByNameContaining(query);
    }

    public Optional<Pizza> findById(int id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getById(int id) {
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);
        if (pizzaAttempt.isEmpty()) {
            // return
        }

        return pizzaAttempt.get();
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {
        pizzaRepository.delete(pizza);
    }

    public void deleteById(int id) {
        Pizza pizza = getById(id);
        pizzaRepository.delete(pizza);
    }
}
