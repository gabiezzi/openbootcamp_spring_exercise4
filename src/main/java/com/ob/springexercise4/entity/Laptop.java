package com.ob.springexercise4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String brand;

    private String processor;

    private String memory;

    private String disk;

    private boolean integratedGraphics;

    public Laptop(String brand, String processor, String memory, String disk, boolean integratedGraphics) {
        this.brand = brand;
        this.processor = processor;
        this.memory = memory;
        this.disk = disk;
        this.integratedGraphics = integratedGraphics;
    }
}
