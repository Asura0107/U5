package u5w2d3.u5w2d3.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Setter
@NoArgsConstructor
@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserPost user;

    public BlogPost(String categoria, String titolo, String cover, String contenuto, int tempoDiLettura, UserPost user) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.user = user;
    }
}
