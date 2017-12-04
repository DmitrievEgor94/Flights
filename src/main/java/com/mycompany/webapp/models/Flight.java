package com.mycompany.webapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flights")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Flight.class)
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "flights_planes",
            joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "plane_id", referencedColumnName = "id")
    )
    private Set<Plane> planes = new HashSet<>();

    public Flight() {
    }

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> ticket) {
        this.tickets = ticket;
    }

    public Set<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(Set<Plane> planes) {
        this.planes = planes;
    }
}
