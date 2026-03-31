package PizzaStore.IasiStore;

import IngredientFactory.BucurestiPizzaIngredientFactory;
import IngredientFactory.IasiPizzaIngredientFactory;
import Pizza.*;
import PizzaStore.*;

public class IasiPizzaStore extends PizzaStore {

    @Override
    protected Pizza CreatePizza(PizzaType type, int pret, String size) {
        switch(type)
        {
            case CLAM :
                IasiPizzaIngredientFactory factory= new IasiPizzaIngredientFactory();
                return new ClamPizza(factory, "clam- Chicago",  pret,  size);

            case PEPPERONI: return new PepperoniPizza(new IasiPizzaIngredientFactory(), "pepperoni- Chicago",  pret,  size);

            case CHEESE: return new CheesePizza(new IasiPizzaIngredientFactory(), "cheese- Chicago",  pret,  size);
        }
        return null;
    }
}