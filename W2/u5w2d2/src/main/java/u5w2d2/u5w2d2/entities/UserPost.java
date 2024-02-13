package u5w2d2.u5w2d2.entities;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class UserPost {
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String dataDiNascita;
    private String avatar;

    public UserPost(String nome, String cognome, String email, String dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.avatar = "https://ui-avatars.com/api/?name="+this.nome+"+"+this.cognome;
    }
}
