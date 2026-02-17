package com.teksystems.custom_authentication_union_bank.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public  String getBalanceDetails () {
        return "Here are the balance details from the DB";
    }

}