package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.core.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private ServiceTicket serviceTicket;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public TicketController(ServiceTicket serviceTicket) {
        this.serviceTicket = serviceTicket;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity update(@RequestBody Ticket ticket) {
        String updateMessage = serviceTicket.update(ticket);

        return updateMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateMessage);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity save(@RequestBody Ticket ticket) {
        String saveMessage = serviceTicket.save(ticket);

        return saveMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveMessage);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        Ticket ticket = serviceTicket.read(id);

        return ticket == null ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(ticket);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        serviceTicket.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getNumberOfEntities() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceTicket.getNumberOfEntities());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity getList(@RequestParam("from") Long firstId, @RequestParam("to") Long lastId) {
        List<Ticket> tickets = serviceTicket.readAll(firstId, lastId);

        return tickets.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(tickets);
    }

    @RequestMapping(value = "/get-by-passenger", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity getPlanesByPassenger(@RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName) {
        List<Ticket> tickets = serviceTicket.getTicketsByPassenger(firstName, lastName);

        return tickets.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(tickets);
    }
}
