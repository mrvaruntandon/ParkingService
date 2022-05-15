package com.github.mrvaruntandon.parkingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @GetMapping("/")
    public String greeting() {
        return "<h1>hello, world!</h1>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h1>hello, user!</h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1>hello, admin!</h1>";
    }
}
