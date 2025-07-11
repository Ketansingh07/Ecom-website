package com.angularspringbootecommerce.backend.dtos;

public class ModDto {

    private String email;
    private String password;

    public ModDto() {
        super();
    }

    public ModDto(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
