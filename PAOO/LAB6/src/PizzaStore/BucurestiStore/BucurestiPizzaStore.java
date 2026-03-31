package PizzaStore.BucurestiStore;

import IngredientFactory.BucurestiPizzaIngredientFactory;
import IngredientFactory.ChicagoPizzaIngredientFactory;
import Pizza.*;
import PizzaStore.*;

public class BucurestiPizzaStore extends PizzaStore {

    @Override
    protected Pizza CreatePizza(PizzaType type, int pret, String size) {
        switch(type)
        {
            case CLAM :
                BucurestiPizzaIngredientFactory factory= new BucurestiPizzaIngredientFactory();
                return new ClamPizza(factory, "clam- Chicago",  pret,  size);

            case PEPPERONI:return  new PepperoniPizza(new BucurestiPizzaIngredientFactory(), "pepperoni- Chicago",  pret,  size);

            case CHEESE: return new CheesePizza(new BucurestiPizzaIngredientFactory(), "cheese- Chicago",  pret,  size);

        }
        return null;
    }
}