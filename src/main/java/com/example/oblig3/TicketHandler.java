package com.example.oblig3;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class TicketHandler {
    private String DBHost = "jdbc:h2:mem:testdb";
    static final String username = "sa";
    static final String Credpassword = "";

    public Connection conn;

    private ArrayList<Ticket> ticketList = new ArrayList<>();
    @PostMapping(path = "/addTicket",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void addTicket(@RequestBody Ticket ticket) {
        System.out.println(ticket);
        ticketList.add(ticket);


    }

    @GetMapping("/getList")
    public ArrayList<Ticket> hey() {
        System.out.println("getReq");
        return ticketList;
    }

    @PostMapping("/delete")
    public void deleteList() {
        ticketList.clear();
    }



}
