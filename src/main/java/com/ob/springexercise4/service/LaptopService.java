package com.ob.springexercise4.service;

import com.ob.springexercise4.entity.Laptop;

import java.util.List;

public interface LaptopService {

    Laptop save(Laptop laptop);

    Laptop update(int id, Laptop laptop);

    Laptop findById(int id);

    List<Laptop> findAll();

    Laptop delete(int id);

}
