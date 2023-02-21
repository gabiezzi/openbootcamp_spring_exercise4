package com.ob.springexercise4.service;

import com.ob.springexercise4.entity.Laptop;

import java.util.List;

public interface LaptopService {

    Laptop save(Laptop laptop) throws Exception;

    Laptop update(int id, Laptop laptop) throws Exception;

    Laptop findById(int id) throws Exception;

    List<Laptop> findAll() throws Exception;

    void delete(int id) throws Exception;

}
