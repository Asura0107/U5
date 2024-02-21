package u5w3d3.demo3.composite;

import java.util.ArrayList;
import java.util.List;

public class Sezione extends Elemento{
    private List<Elemento> elementi;

    public Sezione() {
        this.elementi = new ArrayList<>();
    }

    public void aggiungiElemento(Elemento elemento) {
        elementi.add(elemento);
    }
    @Override
    int numeroPagine() {
        int tot=0;
        for(Elemento elemento : elementi){
            tot += elemento.numeroPagine();
        }
        return tot;
    }
}
