package com.github.mrvaruntandon.parkingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting() {
        return "<h1>hello, world!</h1>";
    }
}
