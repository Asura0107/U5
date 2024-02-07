package pizza.pizza.concrete;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import pizza.pizza.PizzaApplication;
import pizza.pizza.astatto.Listino;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Setter
@Getter
public class Ordine {
    private int numordine;
    private Tavolo tavolo;
    private List<Listino> ordine=new ArrayList<>();
    public StatusOrdine statusOrdine;
    private LocalTime time;
    private double coperto;
    AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(PizzaApplication.class);

    public Ordine(Tavolo tavolo) {
        Random r=new Random();
        this.numordine=r.nextInt(1,100);
        this.tavolo = tavolo;
        this.statusOrdine=StatusOrdine.IN_CORSO;
        this.time=LocalTime.now();
        this.coperto=(double) ctx.getBean("getcoperto");
    }

    public void addordine(Listino item) {
        ordine.add(item);
    }

    public double getpricetotal(){
        double total = ordine.stream().mapToDouble(Listino::getPrice).sum();
        return total;
    }


    public double total(){
        return getpricetotal()+coperto*tavolo.getPosti();
    }


}
