package ru.rsreu.harbor.datalayer.model;

import java.util.Objects;

public class Status {
    private final Long id;
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
