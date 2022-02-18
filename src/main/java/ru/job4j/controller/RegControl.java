package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegControl {

    @GetMapping("/reg")
    public String showRegPage() {
        return "reg";
    }
}
