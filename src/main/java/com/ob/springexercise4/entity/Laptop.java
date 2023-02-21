package com.ob.springexercise4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String brand;

    private String processor;

    private String memory;

    private String disk;

    private boolean integratedGraphics;

    public Laptop(int id, String brand, String processor, String memory, String disk, boolean integratedGraphics) {
        this.id = id;
        this.brand = brand;
        this.processor = processor;
        this.memory = memory;
        this.disk = disk;
        this.integratedGraphics = integratedGraphics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public boolean isIntegratedGraphics() {
        return integratedGraphics;
    }

    public void setIntegratedGraphics(boolean integratedGraphics) {
        this.integratedGraphics = integratedGraphics;
    }
}
