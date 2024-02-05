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
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(PizzaApplication.class);
		List<Pizza> p=(List<Pizza>) ctx.getBean("listpizza");
		List<Topping> t=(List<Topping>) ctx.getBean("toppingList");
		List<Drink> d=(List<Drink>) ctx.getBean("drinkList");
		Menu menu=new Menu(p,t,d);
		menu.menu();

	}

}
