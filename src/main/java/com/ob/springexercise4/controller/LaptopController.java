package com.ob.springexercise4.controller;

import com.ob.springexercise4.entity.Laptop;
import com.ob.springexercise4.service.impl.LaptopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptops/")
public class LaptopController {

    LaptopServiceImpl laptopService;

    @Autowired
    public LaptopController(LaptopServiceImpl laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/save")
    public ResponseEntity<Laptop> save(@RequestBody Laptop laptop) throws Exception{

        if (laptop == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(laptopService.save(laptop));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Laptop> update(@PathVariable int id, @RequestBody Laptop laptop) throws Exception {

        if (id == 0 || laptop.equals(null))
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(laptopService.update(id, laptop));

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Laptop> findById(@PathVariable int id) throws Exception {

        if (id == 0)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(laptopService.findById(id));

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Laptop>> findAll() throws Exception {

        return ResponseEntity.ok(laptopService.findAll());
    }

    @DeleteMapping("/{id}")
    public void deleteLaptop(@PathVariable int id) throws Exception {
        if (id == 0)
            throw new Exception("no element with that id");
        laptopService.delete(id);
    }

}
