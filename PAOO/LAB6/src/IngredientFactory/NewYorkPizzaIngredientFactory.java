package IngredientFactory;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Cheese.ReggianoCheese;
import IngredientFactory.Clams.FrozenClams;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.Dough.ThinCrustDough;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Pepperoni.SlicedPepperoni;
import IngredientFactory.Sauce.PlumTomatoSauce;
import IngredientFactory.Veggies.BlackOlives;
import IngredientFactory.Veggies.IVeggies;
import IngredientFactory.Veggies.Onion;
import IngredientFactory.Veggies.RedPepper;
import IngredientFactory.Sauce.*;
public class NewYorkPizzaIngredientFactory implements IPizzaIngredientFactory{
    @Override
    public IDough CreateDough() {
        return new ThinCrustDough();
    }

    @Override
    public ISauce CreateSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public ICheese CreateCheese() {
        return new ReggianoCheese();
    }

    @Override
    public IVeggies[] CreateVeggies() {
        IVeggies [] v= {new Onion(), new BlackOlives()};
        return v;
    }

    @Override
    public IPepperoni CreatePepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public IClams CreateClams() {
        return new FrozenClams();
    }
    public String toString()
    {
        return "new york";
    }
}
