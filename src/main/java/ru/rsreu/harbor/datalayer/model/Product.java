package ru.rsreu.harbor.datalayer.model;

import java.time.LocalDate;


/**
 * Class describing the essence of products unloaded and loaded by captains
 */
public class Product {
    /**
     * Identifier of product
     */
    private final Long id;

    /**
     * Title of product
     */
    private final String title;

    /**
     * Quantity in one container
     */
    private final int quantity;

    /**
     * Who brought the product
     */
    private final User captain;

    /**
     * Which pier the goods were brought to
     */
    private final Pier pier;

    /**
     * When the product arrived
     */
    private final LocalDate arrivalDate;

    /**
     * When the product depart
     */
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
