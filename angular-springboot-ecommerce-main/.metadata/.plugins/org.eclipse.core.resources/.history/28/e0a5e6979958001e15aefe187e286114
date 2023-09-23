package com.angularspringbootecommerce.backend.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "mod_roles")
public class ModRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private Long id;
    private String authority;
    public ModRole(){
        super();
    }
    public ModRole(String authority){
        this.authority = authority;
    }
    public ModRole(Long id, String authority){
        this.id = id;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority){
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
