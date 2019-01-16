package com.pip_coursework.entity;

import javax.persistence.*;

@Entity
@Table(name ="Items")
public class Item {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    @Column(name = "Description")
    private String description;

    public String getDescription() {
        return description;
    }

    @Column(name = "Price", nullable = false)
    private double price;

    public double getPrice() {
        return price;
    }

    @Column(name = "Weight", nullable = false)
    private double weight;

    public double getWeight() {
        return weight;
    }

    protected Item(){}

    public Item(String name, String description,
                double price, double weight){
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%s Название предмета - '%s', цена - %s, вес - %s. %s", id, name, price, weight, description);
    }
}
