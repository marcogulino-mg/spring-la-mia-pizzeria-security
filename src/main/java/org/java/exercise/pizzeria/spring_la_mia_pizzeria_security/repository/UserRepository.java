package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.repository;

import java.util.Optional;

import org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
