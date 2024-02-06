package pizza.pizza.concrete;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

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
    private List<Pizza> ordinepizza;
    private List<Drink> ordinedrink;
    private List<Topping> ordinetopping;
    public StatusOrdine statusOrdine;
    private LocalTime time;

    public Ordine(Tavolo tavolo) {
        Random r=new Random();
        this.numordine=r.nextInt(1,100);
        this.tavolo = tavolo;
        this.statusOrdine=StatusOrdine.IN_CORSO;
        this.time=LocalTime.now();
        this.ordinepizza = new ArrayList<>();
        this.ordinedrink = new ArrayList<>();
        this.ordinetopping = new ArrayList<>();
    }

    public void addpizza(Pizza itempizza) {
        ordinepizza.add(itempizza);
    }
    public void adddrink(Drink itemdrink) {
        ordinedrink.add(itemdrink);
    } public void addtopping(Topping itemtopping) {
        ordinetopping.add(itemtopping);
    }

    public double getpricetotal(){
        double totalPricepizza = ordinepizza.stream().mapToDouble(Pizza::getPrice).sum();
        double totalPricedrink = ordinedrink.stream().mapToDouble(Drink::getPrice).sum();
        double totalPricetopping = ordinetopping.stream().mapToDouble(Topping::getPrice).sum();
        double total= totalPricedrink+totalPricetopping+totalPricepizza;
        return total;
    }

    public void print(){
        System.out.println("il totatele è: "+ getpricetotal());
    }

}
