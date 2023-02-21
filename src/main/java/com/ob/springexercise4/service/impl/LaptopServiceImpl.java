package com.ob.springexercise4.service.impl;

import com.ob.springexercise4.entity.Laptop;
import com.ob.springexercise4.repository.LaptopRepository;
import com.ob.springexercise4.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {

    private LaptopRepository laptopRepository;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Transactional
    @Override
    public Laptop save(Laptop laptop) {

        Optional<Laptop> optLaptop = laptopRepository.findById(laptop.getId());

        return  optLaptop.orElse(null);

    }

    @Transactional
    @Override
    public Laptop update(int id, Laptop laptop) {
        return null;
    }

    @Override
    public Laptop findById(int id) {
        return null;
    }

    @Override
    public List<Laptop> findAll() {
        return null;
    }

    @Transactional
    @Override
    public Laptop delete(int id) {
        return null;
    }
}
