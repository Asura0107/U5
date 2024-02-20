package com.example.u5w3d1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public String avatar;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    @OneToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;

    public Dipendente(String avatar,String username, String name, String surname, String email, Dispositivo dispositivo, String password) {
        this.avatar=avatar;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dispositivo = dispositivo;
        this.password=password;
    }
}
