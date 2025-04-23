package com.example.esport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "TICKET")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer buyer;
    private float price;
    @Column(name = "is_multipass", nullable = false)
    private boolean isMultipass;
    @Column(name = "dte_reservation", nullable = false)
    private LocalDate reservationDate;
    @Column(name = "name_user")
    private String nameUser; // nom de l’utilisateur réel du billet



    public Ticket(float price, boolean isMultipass, LocalDate reservationDate, String nameUser, Event event, Customer buyer) {
        this.price = price;
        this.isMultipass = isMultipass;
        this.reservationDate = reservationDate;
        this.nameUser = nameUser;
        this.event = event;
        this.buyer = buyer;
    }
}

