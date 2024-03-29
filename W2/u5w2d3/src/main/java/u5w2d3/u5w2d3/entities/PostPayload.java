package u5w2d3.u5w2d3.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record PostPayload(
        @NotEmpty
//        @Size(min = 3, max = 20, message = "la categoria deve essere compresa tra 3 e 20 caratteri")
        String categoria,
        @NotEmpty(message = "la categoria è obbligatorio!")
        @Size(min = 3, max = 20, message = "la categoria deve essere compresa tra 3 e 20 caratteri")
        String titolo,
        String cover,
        @NotEmpty(message = "il contenuto è obbligatorio!")
        @Size(min = 3, max = 20, message = "il contenuto deve essere compresa tra 3 e 20 caratteri")
        String contenuto,
        @NotNull(message = "il tempoDiLettura è obbligatorio!")
        int tempoDiLettura,
        @NotNull(message = "lo userid è obbligatorio!")
        long userid
) {
}
