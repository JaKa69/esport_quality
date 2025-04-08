package com.example.esport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstname;
    private String lastName;
    private String password;
    @Column(nullable = false, unique = true)
    private String mail;
    @OneToMany(mappedBy = "buyer")
    private List<Ticket> purchasedTickets;
}

