package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "discount")
public class Discount {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message = "Discount title must not be null, nor empty or blank")
    @Column(name = "title", nullable = false, length = 50)
    String title;

    @FutureOrPresent(message = "The starting date cannot be set in the past")
    @NotNull(message = "The starting date cannot be null")
    LocalDateTime startDate;

    @FutureOrPresent(message = "The starting date cannot be set in the past")
    @NotNull(message = "The ending date cannot be null")
    LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    @JsonBackReference
    private Pizza pizza;

    // Methods
    // INFO: Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Pizza getPizza() {
        return pizza;
    }

    // INFO: Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
