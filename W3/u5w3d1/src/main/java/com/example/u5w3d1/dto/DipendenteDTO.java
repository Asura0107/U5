package com.example.u5w3d1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotEmpty(message = "l'avatar è obbligatorio")
        String avatar,
        @NotEmpty(message = "lo username è obbligatorio")
        String username,
        @NotEmpty(message = "il nome è obbligatorio")
        @Size(min=3,max=20, message ="il nome deve essere compreso tra 3 e 20 caratteri" )
        String name,
        @NotEmpty(message = "il surname è obbligatorio")
        @Size(min=3,max=20, message ="il surname deve essere compreso tra 3 e 20 caratteri" )
        String surname,
        @NotEmpty(message = "l'email è obbligatorio")
        @Email(message = "l'email inserita non è un indirizzo valido")
        String email,
        @NotEmpty(message = "la password è obbligatorio")
        String password,
        long dispositivo

) {
}
