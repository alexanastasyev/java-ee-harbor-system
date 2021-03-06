package ru.rsreu.harbor.datalayer.model;

import java.util.Objects;

/**
 * Class describing the entity of request status
 */
public class RequestStatus {
    /**
     * Identifier of status
     */
    private final Long id;

    /**
     * Title of status
     */
    private final String title;

    public RequestStatus(Long id, String title) {
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
        RequestStatus that = (RequestStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
