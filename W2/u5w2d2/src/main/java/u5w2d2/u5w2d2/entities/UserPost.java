package u5w2d2.u5w2d2.entities;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPost {
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String dataDiNascita;
    private String avatar;


}
