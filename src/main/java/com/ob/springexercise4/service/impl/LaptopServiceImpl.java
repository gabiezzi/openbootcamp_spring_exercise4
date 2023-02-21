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

    @Autowired
    private LaptopRepository laptopRepository;

    /* @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }*/

    @Transactional
    @Override
    public Laptop save(Laptop laptop) throws Exception {

        Optional<Laptop> optLaptop = laptopRepository.findById(laptop.getId());

        if (optLaptop.isPresent())
            throw new Exception("There's already an element with this id");

        return laptopRepository.save(laptop);




    }

    @Transactional
    @Override
    public Laptop update(int id, Laptop laptop) throws Exception {

        Optional<Laptop> optLaptop = laptopRepository.findById(id);

        if (optLaptop.isPresent())
            laptopRepository.save(laptop);

        throw new Exception("No laptop with this id");


    }

    @Override
    public Laptop findById(int id) throws Exception {

        Optional<Laptop> optLaptop = laptopRepository.findById(id);

        if (optLaptop.isEmpty())
            throw new Exception("No content with that id");

        return optLaptop.get();

    }

    @Override
    public List<Laptop> findAll() throws Exception {

        List<Laptop> optLaptops = laptopRepository.findAll();

        if (optLaptops.isEmpty())
            throw new Exception("List of laptops is empty");

        return optLaptops;
    }

    @Transactional
    @Override
    public void delete(int id) throws Exception {

        laptopRepository.delete(findById(id));

    }
}
