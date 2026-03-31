package PizzaStore.ChicagoStore;

import IngredientFactory.ChicagoPizzaIngredientFactory;
import Pizza.*;
import PizzaStore.*;

public class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza CreatePizza(PizzaType type, int pret, String size) {
        switch(type)
        {
            case CLAM :
                ChicagoPizzaIngredientFactory factory= new ChicagoPizzaIngredientFactory();
                return new ClamPizza(factory, "clam- Chicago",  pret,  size);

            case PEPPERONI: return new PepperoniPizza(new ChicagoPizzaIngredientFactory(), "pepperoni- Chicago",  pret,  size);

            case CHEESE: return new CheesePizza(new ChicagoPizzaIngredientFactory(), "cheese- Chicago",  pret,  size);
        }
        return null;
    }
}
