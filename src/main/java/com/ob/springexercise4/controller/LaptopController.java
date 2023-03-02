package com.ob.springexercise4.controller;

import com.ob.springexercise4.entity.Laptop;
import com.ob.springexercise4.service.impl.LaptopServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@Api(value = "Laptop Controler")
public class LaptopController {


    LaptopServiceImpl laptopService;

    @Autowired
    public LaptopController(LaptopServiceImpl laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "Create a new laptop")
    public ResponseEntity<Laptop> save(@ApiParam(value = "Body with the properties of one laptop element",required = true) @RequestBody Laptop laptop) throws Exception{

        if (laptop == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(laptopService.save(laptop));

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing laptop by ID")
    public ResponseEntity<Laptop> update(@ApiParam(value = "Element primary key", required = true) @PathVariable int id,
                                         @ApiParam(value = "Body of the laptop updated", required = true) @RequestBody Laptop laptop) throws Exception {

        if (id == 0 || laptop == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(laptopService.update(id, laptop));

    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Get a laptop by ID")
    public ResponseEntity<Laptop> findById(@ApiParam(value = "Element primary key", required = true) @PathVariable int id) throws Exception {

        if (id == 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(laptopService.findById(id));

    }
    @GetMapping("/getAll")
    @ApiOperation(value = "Get the list of existing laptops")
    public ResponseEntity<List<Laptop>> findAll() throws Exception {

        return ResponseEntity.ok(laptopService.findAll());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a laptop by ID")
    public ResponseEntity<Laptop> deleteLaptop(@ApiParam(value = "Element primary key" , required = true) @PathVariable int id) throws Exception {

        if (id == 0)
            return ResponseEntity.badRequest().build();

        laptopService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @ApiIgnore
    @DeleteMapping()
    @ApiOperation(value = "Delete all the existing laptops")
    public ResponseEntity<Laptop> deleteAll() throws Exception {

        laptopService.deleteAll();

        return ResponseEntity.noContent().build();

    }

}
