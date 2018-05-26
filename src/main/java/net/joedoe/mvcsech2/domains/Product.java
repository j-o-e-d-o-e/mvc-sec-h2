package net.joedoe.mvcsech2.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private String icon;
    private String color;
    @NotNull
    private double price;

    public Product(String title, String description, String icon, String color, double price) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.color = color;
        this.price = price;
    }
}
