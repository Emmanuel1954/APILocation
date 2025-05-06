package com.api.demo.dtos;

public class UsuarioInfoDTO {
    private String login;
    private String password;
    private String apikey;

    public UsuarioInfoDTO(String login, String password, String apikey) {
        this.login = login;
        this.password = password;
        this.apikey = apikey;
    }

    // Getters
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getApikey() {
        return apikey;
    }
}