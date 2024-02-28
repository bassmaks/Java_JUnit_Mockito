package edu.school21.models;

import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private boolean status_authentication;

    public User(long id, String login, String password, boolean status_auth) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status_authentication = status_auth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatusAuthentication() {
        return status_authentication;
    }

    public void setStatusAuthentication(boolean status_auth) {
        this.status_authentication = status_auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && status_authentication == user.status_authentication
                && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, status_authentication);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status_auth=" + status_authentication +
                '}';
    }
}
