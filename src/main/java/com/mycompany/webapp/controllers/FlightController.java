package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.services.core.ServiceFlight;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private ServiceFlight serviceFlight;

    @Autowired
    public FlightController(ServiceFlight serviceFlight) {
        this.serviceFlight = serviceFlight;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity update(@RequestBody Flight flight) {
        String updateMessage = serviceFlight.update(flight);

        return updateMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateMessage);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity save(@RequestBody Flight flight) {
        String saveMessage = serviceFlight.save(flight);

        return saveMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveMessage);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity get(@PathVariable(value = "id") Long id, Session session) {
        Flight flight = serviceFlight.read(id);

        return flight == null ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(flight);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable(value = "id") long id) {
        serviceFlight.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getNumberOfEntities() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceFlight.getNumberOfEntities());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity getList(@RequestParam("from") Long firstId, @RequestParam("to") Long lastId) {
        List<Flight> flights = serviceFlight.readAll(firstId, lastId);

        return flights.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(flights);
    }

    @RequestMapping(value = "/get-by-plane", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity getFlightsByPlane(@RequestParam("plane-number") String planeNumber) {
        List<Flight> flights = serviceFlight.getFlightsByPlane(planeNumber);

        return flights.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(flights);
    }
}
