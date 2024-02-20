package com.example.u5w3d1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DispositivoDTO(
        @NotEmpty(message = "il tipo è obbligatorio")
        @Size(min=2,max=20, message ="il tipo deve essere compreso tra 3 e 20 caratteri" )
        String tipo,
        @NotEmpty(message = "la disponibilità è obbligatoria")
        String disponibile
) {
}
