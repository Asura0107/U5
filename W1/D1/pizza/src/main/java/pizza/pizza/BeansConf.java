package pizza.pizza;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pizza.pizza.concrete.Drink;
import pizza.pizza.concrete.Pizza;
import pizza.pizza.concrete.Topping;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeansConf {
    @Bean
    Topping cheese() {
        return new Topping("cheese", 92, 0.69);
    }

    @Bean
    Topping tomato() {
        return new Topping("tomato", 90, 0.60);
    }

    @Bean
    Topping onions() {
        return new Topping("onions", 22, 0.69);
    }

    @Bean
    Topping ham() {
        return new Topping("ham", 35, 0.99);
    }

    @Bean
    Topping pineapple() {
        return new Topping("pineapple", 24, 0.79);
    }

    @Bean
    Topping pep() {
        return new Topping("salami", 86, 0.99);
    }

    @Bean
    Pizza getmargherita() {
        Pizza margherita = new Pizza("Margherita", 1000, 4);
        margherita.addToppings(cheese());
        margherita.addToppings(tomato());
        return margherita;
    }

    @Bean
    Pizza gethawaiana() {
        Pizza hawaiana = new Pizza("Hawaiana", 1000, 4);
        hawaiana.addToppings(ham());
        hawaiana.addToppings(pineapple());
        hawaiana.addToppings(cheese());
        hawaiana.addToppings(tomato());
        return hawaiana;
    }

    @Bean
    Pizza getsalami() {
        Pizza salami = new Pizza("Salami", 1000, 4);
        salami.addToppings(pep());
        salami.addToppings(cheese());
        salami.addToppings(tomato());
        return salami;
    }

    @Bean
    Drink getlemonade() {
        return new Drink("Lemonade(0.33l)", 128, 1.29);
    }

    @Bean
    Drink getwater() {
        return new Drink("Water(0.5l)", 0, 1.29);
    }

    @Bean
    Drink getwine() {
        return new Drink("Lemonade(0.75l, 13%)", 607, 7.49);
    }

    @Bean
    List<Pizza> listpizza() {
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(gethawaiana());
        pizzas.add(getsalami());
        pizzas.add(getmargherita());
        return pizzas;
    }

    @Bean
    List<Topping> toppingList() {
        List<Topping> toppings = new ArrayList<>();
        toppings.add(cheese());
        toppings.add(ham());
        toppings.add(onions());
        toppings.add(pineapple());
        toppings.add(pep());
        return toppings;
    }

    @Bean
    List<Drink> drinkList() {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(getwater());
        drinks.add(getwine());
        drinks.add(getlemonade());
        return drinks;
    }

}
