package com.teksystems.inmemoryusers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @GetMapping("/adminHR")
    public String adminHR() {
        return "Admin HR Access Granted";
    }

    @GetMapping("/manager")
    public String manager() {
        return "Manager Access Granted";
    }

    @GetMapping("/employee")
    public String employee() {
        return "Employee Access Granted";
    }
}