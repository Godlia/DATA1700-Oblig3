package com.example.oblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TicketController {
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
        System.out.println("Deleted All Tickets");
        ticketRepository.deleteAllTickets();
    }

    @DeleteMapping(path = "/deleteEntry")
    public void deleteTicket(@RequestBody String request) {
        int intVal = 0;
        try {
            intVal = Integer.parseInt(request);
            ticketRepository.deleteTicket(intVal);
            System.out.println("Deleted Entry with ID: " + intVal);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(intVal);
    }

    @GetMapping("/findAll")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @PutMapping(path = "/updateTicket",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void updateTicket(@RequestBody Map<String, Object> request) {
        int ID = Integer.parseInt(request.get("id").toString());
        String column = request.get("column").toString();
        String value = request.get("newValue").toString();
        switch (column) {
            case "moviename":
                break;
            case "amount":
                if (value.matches("[0-9]+")) {
                    break;
                } else {
                    System.out.println("Invalid Value");
                    return;
                }
            case "firstname":
                break;
            case "lastname":
                break;
            case "phonenumber":
                if(value.matches("^[0-9]{8}$")) {
                    break;
                } else {
                    System.out.println("Invalid Value");
                    return;
                }
            case "email":
                if (value.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
                    break;
                } else {
                    System.out.println("Invalid Value");
                    return;
                }
            default:
                System.out.println("Invalid Column");
                return;
        }

        ticketRepository.updateTicket(ID, column, value);
    }


}
