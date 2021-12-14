package ru.rsreu.harbor.datalayer.model;

import java.util.Objects;

/**
 * Class describing user statuses
 * - ACTIVE
 * - BLOCKED
 * - DELETED
 */
public class Status {
    /**
     * Identifier of status
     */
    private final Long id;

    /**
     * Title of status
     */
    private final String title;

    public Status(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return id.equals(status.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
