package ru.rsreu.harbor.datalayer.configuration;

public final class ServerDbConfiguration extends DbConfiguration {
    private final String url;
    private final String user;
    private final String password;

    public ServerDbConfiguration(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
