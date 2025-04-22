package com.example.esport.repository;

import com.example.esport.model.Customer;
import com.example.esport.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findById(Long id);
    Optional<Ticket> findByBuyerAndIsMultipassTrue(Customer buyer);
}
