package com.memory.roomreserve.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private float price;
    private int size;
    private boolean available;


    public Room(String name, float price, int size) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.available = true;
    }

    public Room() {

    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }
}
