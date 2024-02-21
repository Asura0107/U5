package u5w3d3.demo3.composite;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Libro {
    private List<Elemento> elementoList;
    private List<Autore> autoreList;
    private double prezzo;

    public Libro(double prezzo) {
        this.elementoList = new ArrayList<>();
        this.autoreList = new ArrayList<>();
        this.prezzo = prezzo;
    }

    public void aggiungiAutore(Autore autore) {
        autoreList.add(autore);
    }

    public void aggiungiSezione(Elemento elemento) {
        elementoList.add(elemento);
    }

    public int totpagine(){
        int tot=0;
        for(Elemento elemento : elementoList){
            tot += elemento.numeroPagine();
        }
        return tot;
    }
}
