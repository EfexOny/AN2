package IngredientFactory;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Veggies.IVeggies;
import IngredientFactory.Sauce.*;

public interface IPizzaIngredientFactory {
    public IDough CreateDough();
    public ISauce CreateSauce();
    public ICheese CreateCheese();
    public IVeggies[]  CreateVeggies();
    public IPepperoni CreatePepperoni();
    public IClams CreateClams();
}
