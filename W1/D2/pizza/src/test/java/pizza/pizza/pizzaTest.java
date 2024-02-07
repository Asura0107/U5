package pizza.pizza;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizza.pizza.concrete.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class pizzaTest {
    AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(PizzaApplication.class);

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BEFORE ALL");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AFTER ALL");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BEFORE EACH");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AFTER EACH");
    }

    @Test
    public void prezzototale(){
        Tavolo tavol2=(Tavolo)ctx.getBean("tavolo2");
        Pizza margherita=(Pizza)ctx.getBean("getmargherita");
        Drink wine=(Drink)ctx.getBean("getwine");
        Ordine ordine1=new Ordine(tavol2);
        ordine1.addordine(margherita);
        ordine1.addordine(wine);
        System.out.println(margherita.getPrice());
        System.out.println(tavol2.getPosti());
        System.out.println(ordine1.total());
        assertEquals(19.490000000000002,ordine1.total());


    }

    @Test
    public void calccalories(){
        Pizza hawaiana=(Pizza) ctx.getBean("gethawaiana");
        Topping onions=(Topping) ctx.getBean("onions");
        Topping pep=(Topping) ctx.getBean("pep");
        System.out.println(hawaiana.getCalories());
        hawaiana.addToppings(pep);
        System.out.println(pep.getCalories());
        hawaiana.addToppings(onions);
        System.out.println(onions.getCalories());
        System.out.println(hawaiana.calories());
        assertEquals(1349.0,hawaiana.calories());

    }

    @Test
    public void pricepizza(){
        Pizza margherita=(Pizza)ctx.getBean("getmargherita");
        Topping onions=(Topping) ctx.getBean("onions");
        Topping pep=(Topping) ctx.getBean("pep");
        margherita.addToppings(onions);
        margherita.addToppings(pep);
        System.out.println(onions.getPrice());
        System.out.println(pep.getPrice());
        System.out.println(margherita.price());
        assertEquals(6.97,margherita.price());
    }

//    @ParameterizedTest
//    @CsvSource({"1, 1, 2", "2, 2, 4"})
//    public void testParameterizedSum(int add1, int add2, int expectedResult) {
//        int result = CustomMath.sum(add1, add2);
//        assertEquals(expectedResult, result);
//    }

}
