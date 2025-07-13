package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.repository;

import java.util.List;

import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByNameContaining(String name);
}
