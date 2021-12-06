package ru.rsreu.harbor.datalayer.model;

import java.time.LocalDate;

public class Product {
    private final Long id;
    private final String title;
    private final int quantity;
    private final User captain;
    private final Pier pier;
    private final LocalDate arrivalDate;
    private final LocalDate departureDate;

    public Product(Long id, String title, int quantity, User captain, Pier pier, LocalDate arrivalDate, LocalDate departureDate) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.captain = captain;
        this.pier = pier;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getCaptain() {
        return captain;
    }

    public Pier getPier() {
        return pier;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }
}
