package com.transplantchain.entity;

import jakarta.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String abhaId;
    
    private String name;
    private String password;
    
    public Patient() {}
    public Patient(String abhaId, String password, String name) {
        this.abhaId = abhaId;
        this.password = password;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getAbhaId() { return abhaId; }
    public void setAbhaId(String abhaId) { this.abhaId = abhaId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
