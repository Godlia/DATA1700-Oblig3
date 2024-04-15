package com.example.oblig3;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketHandler {
    @Autowired
    TicketRepository ticketRepository;

    @PostMapping(path = "/addTicket",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void addTicket(@RequestBody Ticket ticket) {
        ticketRepository.insertTicket(ticket);
    }

    @DeleteMapping("/deleteAll")
    public void deleteList() {
        System.out.println("wa");
        ticketRepository.deleteAllTickets();
    }

    @DeleteMapping(path = "/deleteEntry")
    public void deleteTicket(@RequestBody String request) {
        int intVal = 0;
        try {
            intVal = Integer.parseInt(request);
            ticketRepository.deleteTicket(intVal);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(intVal);
    }

    @GetMapping("/findAll")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


}
