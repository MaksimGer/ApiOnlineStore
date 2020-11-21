package org.example.onlinestore.domain.entityes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usr")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public void setPassword(String password) { this.password = password; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) { this.username = username; }

    public boolean isAdmin(){
        return roles.contains("ADMIN");
    }
}
