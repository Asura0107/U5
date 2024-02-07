package pizza.pizza.concrete;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import pizza.pizza.astatto.Listino;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Setter
@Getter
//@PropertySource("application.properties")
public class Ordine {
    private int numordine;
    private Tavolo tavolo;
    private List<Listino> ordine=new ArrayList<>();
    public StatusOrdine statusOrdine;
    private LocalTime time;

    public Ordine(Tavolo tavolo) {
        Random r=new Random();
        this.numordine=r.nextInt(1,100);
        this.tavolo = tavolo;
        this.statusOrdine=StatusOrdine.IN_CORSO;
        this.time=LocalTime.now();
    }

    public void addordine(Listino item) {
        ordine.add(item);
    }

    public double getpricetotal(){
        double total = ordine.stream().mapToDouble(Listino::getPrice).sum();
        return total;
    }

    public void print(){
        System.out.println("il totatele è: "+ getpricetotal());
    }

}
