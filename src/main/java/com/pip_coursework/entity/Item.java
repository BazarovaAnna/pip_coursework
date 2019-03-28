package com.pip_coursework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private List<Inventory> inventoryList;

    public void setInventory(List<Inventory> inventory) {
        this.inventoryList = inventory;
    }

    public List<Inventory> getInventory() {
        return inventoryList;
    }

    public void addInventory(Inventory inventory) {
        this.inventoryList.add(inventory);
    }

    public void removeInventory(Inventory inventory) {
        this.inventoryList.remove(inventory);
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
        return String.format("{\"id\":\"%s\",\"name\":\"%s\",\"description\":\"%s\",\"price\":%s,\"weight\":%s}", id, name, description, price, weight);
    }
}
