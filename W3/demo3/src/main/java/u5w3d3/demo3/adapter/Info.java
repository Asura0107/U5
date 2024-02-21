package u5w3d3.demo3.adapter;

import java.util.Date;

public class Info {
    private String nome;
    private String congnome;
    private Date dataNascita;

    public Info(String nome, String congnome, Date dataNascita) {
        this.nome = nome;
        this.congnome = congnome;
        this.dataNascita = dataNascita;
    }

    public String getNome() {
        return nome;
    }

    public String getCongnome() {
        return congnome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCongnome(String congnome) {
        this.congnome = congnome;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
}
