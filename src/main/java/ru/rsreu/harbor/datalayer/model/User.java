package ru.rsreu.harbor.datalayer.model;

public final class User {
    private final Long id;
    private final String login;
    private final String password;
    private final Role role;
    private final Status status;

    public User(Long id, String login, String password, Role role, Status status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
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
}
