package com.mycompany.webapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "planes")
@NamedQuery(
        name = "findPlanesForPassenger",
        query = "SELECT p FROM Plane p JOIN p.tickets t JOIN t.passenger pas " +
                "WHERE pas.firstName = :firstName AND pas.lastName = :lastName ORDER BY p.planeNumber"
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Plane.class)
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "plane_number", nullable = false)
    private String planeNumber;

    @ManyToMany(mappedBy = "planes")
    @JsonIgnore
    private Set<Flight> flights = new HashSet<>();

    @OneToMany(mappedBy = "plane", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnore
    private Set<Ticket> tickets = new HashSet<>();

    public Plane() {
    }

    public Plane(String planeNumber) {
        this.planeNumber = planeNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
