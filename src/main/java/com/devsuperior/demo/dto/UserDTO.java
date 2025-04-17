package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.Role;
import com.devsuperior.demo.entities.User;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;

    @Email(message = "Insira um email válido!")
    private String email;
    private String password;

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(){}

    public UserDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        email = entity.getEmail();
        password = entity.getPassword();
        for (Role r : entity.getRoles()){
            roles.add(new RoleDTO(r));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<RoleDTO> getRoles() {
        return roles;
    }

}
