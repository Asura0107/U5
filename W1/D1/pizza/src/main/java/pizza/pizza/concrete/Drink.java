package pizza.pizza.concrete;

import lombok.Getter;
import lombok.Setter;
import pizza.pizza.astatto.Listino;
@Getter
@Setter
public class Drink extends Listino {
    public Drink(String name, double calories, double price) {
        super(name, calories, price);
    }
    public String toString(){
        return name + " " + calories + " " + price;
    }

}
