package com.example.esport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "EVENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "dte_start", nullable = false)
    private LocalDate startDate;
    @Column(name = "dte_end", nullable = false)
    private LocalDate endDate;
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;
}
