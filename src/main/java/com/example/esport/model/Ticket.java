package com.example.esport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    @JoinColumn(name = "id_1", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "id_2", nullable = false)
    private Customer buyer;
    private BigDecimal price;
    @Column(name = "is_multipass", nullable = false)
    private boolean isMultipass;
    @Column(name = "dte_reservation", nullable = false)
    private LocalDate reservationDate;
    @Column(name = "name_user")
    private String nameUser; // nom de l’utilisateur réel du billet
}

