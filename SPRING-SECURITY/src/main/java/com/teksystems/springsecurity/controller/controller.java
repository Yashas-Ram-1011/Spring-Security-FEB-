package com.teksystems.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller
{



    @GetMapping("/adminHR")
    public String adminHR() {
        return "adminHR";
    }


    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }


    @GetMapping("/employee")
    public String employee() {
        return "employee";
    }


}
