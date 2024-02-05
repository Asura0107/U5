package pizza.pizza.astatto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Listino {
    protected String name;
    protected double calories;
    protected double price;

    public Listino(String name, double calories, double price) {
        this.name = name;
        this.calories = calories;
        this.price = price;
    }
}
