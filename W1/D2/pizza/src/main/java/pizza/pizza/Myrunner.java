package pizza.pizza;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import pizza.pizza.concrete.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Myrunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(PizzaApplication.class);
        List<Pizza> p=(List<Pizza>) ctx.getBean("listpizza");
        List<Topping> t=(List<Topping>) ctx.getBean("toppingList");
        List<Drink> d=(List<Drink>) ctx.getBean("drinkList");
        Menu menu=new Menu(p,t,d);
        menu.menu();

        List<Tavolo> tavoloList=(List<Tavolo>) ctx.getBean("listtavoli");
        List<Tavolo> tavoliLiberi = tavoloList.stream().filter(tavolo -> tavolo.getStatusTavolo() == StatusTavolo.LIBERO).collect(Collectors.toList());
        System.out.println("Salve, i tavoli liberi al momento sono: "+ tavoliLiberi.stream().map(Tavolo::getNumtavolo).map(Object::toString).collect(Collectors.joining(", ")));

        System.out.println("Cosa prendete?");
        System.out.println("una margherita, una hawaiana ed del vino, perfetto");
        Ordine ordine=new Ordine(1, )
    }
}
