package com.angularspringbootecommerce.backend.dtos;

import com.angularspringbootecommerce.backend.models.Mod;
import lombok.Getter;

@Getter
public class ModLoginDto {

    private Long id;
    private Mod user;
    private String jwt;

    public ModLoginDto() {
        super();
    }

    public ModLoginDto(Long id, Mod user, String jwt) {
        this.id = id;
        this.user = user;
        this.jwt = jwt;
    }

    public void setId(Long id) {
        this.id = id;
    }
}