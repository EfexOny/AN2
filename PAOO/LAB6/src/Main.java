import PizzaStore.BucurestiStore.BucurestiPizzaStore;
import PizzaStore.NewYorkStore.NewYorkStore;
import PizzaStore.*;
import Pizza.*;
public class Main {
    public static void main(String[] args) {
        PizzaStore nyStore=new NewYorkStore();
        PizzaStore bucStore=new BucurestiPizzaStore();
        Pizza[] pizzas = {
                nyStore.OrderPizza(PizzaType.CHEESE, 15, "medium"),
                nyStore.OrderPizza(PizzaType.CLAM, 22, "mica"),
                nyStore.OrderPizza(PizzaType.PEPPERONI, 33, "mare"),
                bucStore.OrderPizza(PizzaType.CHEESE, 15, "mica"),
                bucStore.OrderPizza(PizzaType.CLAM, 66, "mare"),
                bucStore.OrderPizza(PizzaType.PEPPERONI,33, "medium"),
        };
        for (Pizza p: pizzas)
        {
            System.out.println(p);
        }
    }
    }