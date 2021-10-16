package ru.rsreu.harbor.datalayer.model;

public final class User {
    private final String login;
    private final String password;
    private final int roleId;

    public User(String login, String password, int roleId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleId() {
        return roleId;
    }
}
