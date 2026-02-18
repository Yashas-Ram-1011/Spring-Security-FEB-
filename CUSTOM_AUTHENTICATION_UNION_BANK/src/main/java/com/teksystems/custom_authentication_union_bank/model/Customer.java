package com.teksystems.custom_authentication_union_bank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter @Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String pwd;
    @Column(name = "role")
    private String role;


    private int failedAttempts;
    private boolean accountNonLocked = true;


    @Column(name = "lock_time")
    private LocalDateTime lockTime;
}