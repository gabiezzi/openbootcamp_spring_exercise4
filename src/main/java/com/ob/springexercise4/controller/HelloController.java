package com.ob.springexercise4.controller;

import com.ob.springexercise4.service.impl.HelloServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloServiceImpl helloService;

    @GetMapping("greetings")
    @Operation(summary = "It gives a greeting to an stranger")
    public ResponseEntity<String> greetings() {

        return ResponseEntity.ok(helloService.returnHello());

    } @GetMapping("behave")
    @Operation(summary = "It gives a behave baby")
    public ResponseEntity<String> behaveBaby() {

        return ResponseEntity.ok(helloService.behaveBaby());

    }
}
