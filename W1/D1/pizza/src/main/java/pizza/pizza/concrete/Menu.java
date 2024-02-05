package pizza.pizza.concrete;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<Pizza> pizzas=new ArrayList<>();
    List<Topping> toppings=new ArrayList<>();
    List<Drink> drinks=new ArrayList<>();

    public Menu(List<Pizza> pizzas, List<Topping> toppings, List<Drink> drinks) {
        this.pizzas = pizzas;
        this.toppings = toppings;
        this.drinks = drinks;
    }
    public void menu(){
        System.out.println("----pizza-----");
        pizzas.stream().forEach(System.out::println);

        System.out.println("----topping----");
        toppings.stream().forEach(System.out::println);

        System.out.println("----drinks----");
        drinks.stream().forEach(System.out::println);

    }
}
