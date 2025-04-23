package com.example.esport.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "home"; // Va chercher templates/home.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Tu pourras créer login.html ensuite
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Pareil pour register.html
    }
}
