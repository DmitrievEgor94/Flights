package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.services.core.ServicePassenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private ServicePassenger servicePassenger;

    @Autowired
    public PassengerController(ServicePassenger servicePassenger) {
        this.servicePassenger = servicePassenger;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity update(@RequestBody Passenger passenger) {
        String updateMessage = servicePassenger.update(passenger);

        return updateMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateMessage);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity save(@RequestBody Passenger passenger) {
        String saveMessage = servicePassenger.save(passenger);

        return saveMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveMessage);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        Passenger passenger = servicePassenger.read(id);

        return passenger == null ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(passenger);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        servicePassenger.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getNumberOfEntities() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(servicePassenger.getNumberOfEntities());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity getList(@RequestParam("from") Long firstId, @RequestParam("to") Long lastId) {
        List<Passenger> passengers = servicePassenger.readAll(firstId, lastId);

        return passengers.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(passengers);
    }

    @RequestMapping(value = "/get-with-several-tickets", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity getPassengersWithSeveralTickets() {
        List<Passenger> passengers = servicePassenger.getPassengersWithSeveralTickets();

        return passengers.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(passengers);
    }
}