package com.example.u5w3d1.entities;

import com.example.u5w3d1.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"password", "credentialsNonExpired", "accountNonExpired", "authorities", "username", "accountNonLocked", "enabled"})
public class Dipendente implements UserDetails {
    //l'interfaccia UserDetails serve per rendere compatibile gli user con le autorizzazioni di spring security
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public String avatar;
    private String username;
    private String name;
    private String surname;
    private String email;
    @OneToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Dipendente(String avatar,String username, String name, String surname, String email, Dispositivo dispositivo, String password) {
        this.avatar=avatar;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dispositivo = dispositivo;
        this.password=password;
        this.role=Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Questo deve ritornare la lista dei ruoli (SimpleGrantedAuthority) dell'utente
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email; // Ritorniamo l'email poiché nel nostro caso non utilizziamo lo username per il login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
