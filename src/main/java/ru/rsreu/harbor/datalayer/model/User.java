package ru.rsreu.harbor.datalayer.model;

import java.util.Objects;

/**
 * Class describing the user entity
 */
public class User {
    /**
     * Identifier of user
     */
    private final Long id;

    /**
     * User login
     */
    private final String login;

    /**
     * User password
     */
    private final String password;

    /**
     * User role
     */
    private final Role role;

    /**
     * User status
     */
    private final Status status;

    /**
     * User online
     */
    private final boolean isOnline;

    public User(Long id, String login, String password, Role role, Status status, boolean isOnline) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
        this.isOnline = isOnline;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isOnline() {
        return isOnline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(role, user.role)
                && Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, status);
    }

}
