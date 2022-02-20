package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControl {

    @GetMapping("/login")
    public String show(@RequestParam(value = "reg", required = false) String reg,
                       Model model) {
        String regMessage = null;
        if (reg != null) {
            regMessage = "Регистрация выполнена";
        }
        model.addAttribute("regMessage", regMessage);
        return "login";
    }
}
