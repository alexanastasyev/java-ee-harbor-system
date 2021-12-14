package ru.rsreu.harbor.datalayer.model;

import java.util.Objects;

/**
 * Class describing the essence of the pier
 */
public class Pier {
    /**
     * Identifier of pier
     */
    private final Long id;

    public Pier(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pier pier = (Pier) o;
        return Objects.equals(id, pier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
