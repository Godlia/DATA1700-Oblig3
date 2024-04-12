package com.example.oblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class TicketRowMapper implements RowMapper<Ticket> {
        @Override
        public Ticket mapRow(ResultSet set, int rowNum) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setID(set.getInt("ID"));
            ticket.setMovieName(set.getString("moviename"));
            ticket.setAmount(set.getInt("amount"));
            ticket.setFirstName(set.getString("firstname"));
            ticket.setLastName(set.getString("lastname"));
            ticket.setPhoneNumber(set.getString("phonenumber"));
            ticket.seteMail(set.getString("email"));
            return ticket;
        }
    }

    public int insertTicket(Ticket ticket) {
        String sql = "INSERT INTO TICKET(moviename, amount, firstname, lastname, phonenumber, email) VALUES (?, ? , ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, ticket.getMovieName(), ticket.getAmount(), ticket.getFirstName(),
        ticket.getLastName(), ticket.getPhoneNumber(), ticket.geteMail());
    }
    public void deleteAllTickets() {
        String sql = "DELETE FROM TICKET";
        jdbcTemplate.update(sql);
    }

    public void deleteTicket(int ID) {
        String sql = "DELETE FROM TICKET WHERE ID = ?";
        jdbcTemplate.update(sql, ID);
    }
    public List<Ticket> findAll() {
        String sql = "SELECT * FROM TICKET";
        return jdbcTemplate.query(sql, new TicketRowMapper());
    }
}
