package pizza.pizza.concrete;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalTime;
import java.util.List;
@Setter
@Getter
//@PropertySource("application.properties")
public class Ordine {
    private Tavolo tavolo;
    private List<Menu> ordinemenu;
    public StatusOrdine statusOrdine;
    private LocalTime time;

    public Ordine(Tavolo tavolo, List<Menu> ordinemenu, StatusOrdine statusOrdine,  LocalTime time) {
        this.tavolo = tavolo;
        this.ordinemenu = ordinemenu;
        this.statusOrdine=statusOrdine;
        this.time=time;
    }

    public void additem(Menu itemmenu) {
        ordinemenu.add(itemmenu);
    }
    public double getpricetotal(){
        double totalPricemenu = ordinemenu.stream().mapToDouble(Menu::getPrice).sum();
//        double totalPrice=totalPricemenu+(@Value("${ordine.coperto}")*tavolo.getPosti());
        return totalPricemenu;
    }

}
