package PizzaStore.NewYorkStore;

import IngredientFactory.ChicagoPizzaIngredientFactory;
import IngredientFactory.NewYorkPizzaIngredientFactory;
import Pizza.*;
import PizzaStore.*;

public class NewYorkStore extends PizzaStore {

    @Override
    protected Pizza CreatePizza(PizzaType type, int pret, String size) {
        switch(type)
        {
            case CLAM :
                NewYorkPizzaIngredientFactory factory= new NewYorkPizzaIngredientFactory();
                return new ClamPizza(factory, "clam- Chicago",  pret,  size);

            case PEPPERONI: return new PepperoniPizza(new NewYorkPizzaIngredientFactory(), "pepperoni- Chicago",  pret,  size);

            case CHEESE: return new CheesePizza(new NewYorkPizzaIngredientFactory(), "cheese- Chicago",  pret,  size);
        }
        return null;
    }
}