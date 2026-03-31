package IngredientFactory;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Cheese.ReggianoCheese;
import IngredientFactory.Clams.FreshClams;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.Dough.ThickCrustDough;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Pepperoni.SlicedPepperoni;
import IngredientFactory.Sauce.*;
import IngredientFactory.Veggies.*;
public class ChicagoPizzaIngredientFactory implements IPizzaIngredientFactory{
    @Override
    public IDough CreateDough() {
        return new ThickCrustDough();
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
        IVeggies []v={new RedPepper(), new EggPlant(), new BlackOlives()};
        return v;
    }

    @Override
    public IPepperoni CreatePepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public IClams CreateClams() {
        return new FreshClams();
    }
    public String toString()
    {
        return "chicago";
    }
}
