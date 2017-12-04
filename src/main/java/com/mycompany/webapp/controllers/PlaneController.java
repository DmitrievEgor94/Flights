package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.ServicePlane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;


@Controller
@RequestMapping("/planes")
public class PlaneController {

    private ServicePlane servicePlane;

    @Autowired
    public PlaneController(ServicePlane servicePlane) {
        this.servicePlane = servicePlane;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity update(@RequestBody Plane plane) {
        String updateMessage = servicePlane.update(plane);

        return updateMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateMessage);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity save(@RequestBody Plane plane) {
        String saveMessage = servicePlane.save(plane);

        return saveMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveMessage);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        Plane plane = servicePlane.read(id);

        return plane == null ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(plane);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        servicePlane.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getNumberOfEntities() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(servicePlane.getNumberOfEntities());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity getList(@RequestParam(name = "from") Long firstId, @RequestParam("to") Long lastId) {
        List<Plane> planes = servicePlane.readAll(firstId, lastId);

        return planes.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(planes);
    }

    @RequestMapping(value = "/get-by-passenger", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public ResponseEntity getPlanesByPassenger(@RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName) {
        List<Plane> planes = servicePlane.getPlanesByPassenger(firstName, lastName);

        return planes.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(planes);
    }
}
