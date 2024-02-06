package pizza.pizza.concrete;

import lombok.Getter;
import lombok.Setter;
import pizza.pizza.astatto.Listino;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Pizza extends Listino {
    List<Topping> toppings=new ArrayList<>();
    public Pizza(String name, double calories, double price) {
        super(name, calories, price);
    }

    public void addToppings(Topping topping) {
        toppings.add(topping);
    }


    public String toString() {
        return  "Pizza "+ name+ "(pomodoro, mozzarella, salame "+toppings.stream().map(Topping::getName).collect(Collectors.joining(", "))+" )   "+ (calories+toppings.stream().mapToDouble(Topping::getCalories).sum())+"   "+ (price + toppings.stream().mapToDouble(Topping::getPrice).sum());
    }

}
