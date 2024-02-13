package u5w2d2.u5w2d2.entities;

import lombok.*;

import java.sql.Time;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    private long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
}
