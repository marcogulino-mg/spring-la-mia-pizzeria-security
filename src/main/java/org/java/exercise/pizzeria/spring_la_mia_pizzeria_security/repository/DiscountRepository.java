package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.repository;

import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
