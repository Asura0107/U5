package com.example.u5w3d1.entities;

import com.example.u5w3d1.enums.DisponibileDisp;
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
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
    @Enumerated(EnumType.STRING)
    private DisponibileDisp disponibileDisp;

    public Dispositivo(String tipo, DisponibileDisp disponibileDisp) {
        this.tipo = tipo;
        this.disponibileDisp = disponibileDisp;
    }

}
