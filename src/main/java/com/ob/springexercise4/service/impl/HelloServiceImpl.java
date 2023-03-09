package com.ob.springexercise4.service.impl;

import com.ob.springexercise4.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String returnHello() {
        return "Welcome, Stranger!";
    }
    @Override
    public String behaveBaby() {
        return "Oh, behave Baby!";
    }
}
