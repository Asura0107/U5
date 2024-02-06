package pizza.pizza.concrete;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Tavolo {
    private int posti;
    private int numtavolo;
    public StatusTavolo statusTavolo;

    public Tavolo(int posti, int numtavolo, StatusTavolo statusTavolo) {
        this.posti = posti;
        this.numtavolo = numtavolo;
        this.statusTavolo=statusTavolo;
    }

    @Override
    public String toString() {
        return "Tavolo" + numtavolo+ " ,posti "+ posti;
    }
}
