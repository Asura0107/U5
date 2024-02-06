package pizza.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizza.pizza.concrete.Drink;
import pizza.pizza.concrete.Menu;
import pizza.pizza.concrete.Pizza;
import pizza.pizza.concrete.Topping;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PizzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaApplication.class);

	}

}
