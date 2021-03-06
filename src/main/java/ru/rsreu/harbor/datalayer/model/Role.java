package ru.rsreu.harbor.datalayer.model;

import java.util.Objects;


/**
 * Class describing user roles
 * - ADMIN
 * - MODER
 * - CAPTAIN
 * - DISPATCHER
 */
public class Role {
    /**
     * Identifier of role
     */
    private final Long id;

    /**
     * Title of role
     */
    private final String title;

    public Role(Long id, String title) {
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
        Role role = (Role) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
