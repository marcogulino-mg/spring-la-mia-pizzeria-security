package org.java.exercise.pizzeria.spring_la_mia_pizzeria_security.model;

import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "pizza")
public class Pizza {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name must not be null, nor empty or blank")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Lob
    @Column(name = "description", nullable = false, length = 100)
    @NotBlank(message = "Description must not be null, nor empty or blank")
    private String description;

    @Column(name = "photo_url", nullable = false, length = 100)
    @NotBlank(message = "Photo_url must not be null, nor empty or blank")
    private String photo_url;

    @NotNull
    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    // INFO: Relationships
    @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    private List<Discount> discounts;

    @ManyToMany
    @JoinTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @JsonBackReference
    private List<Ingredient> ingredients;

    // Methods
    // INFO: Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    // INFO: Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // INFO: Others Methods
    @Override
    public String toString() {
        return String.format("%s %s %.2f", this.name, this.description, this.price);
    }
}
