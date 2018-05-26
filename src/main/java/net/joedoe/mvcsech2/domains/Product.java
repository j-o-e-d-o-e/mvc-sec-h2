package net.joedoe.mvcsech2.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String icon;
    private String color;
    private double price;

    public Product(String title, String description, String icon, String color, double price) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.color = color;
        this.price = price;
    }
}
