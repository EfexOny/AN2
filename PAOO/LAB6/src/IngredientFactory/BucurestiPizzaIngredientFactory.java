package IngredientFactory;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Cheese.Mozzarella;
import IngredientFactory.Clams.FrozenClams;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.Dough.ThickCrustDough;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Pepperoni.SlicedPepperoni;
import IngredientFactory.Sauce.*;
import IngredientFactory.Veggies.*;
public class BucurestiPizzaIngredientFactory implements IPizzaIngredientFactory{
    @Override
    public IDough CreateDough()
    {
        return new ThickCrustDough();
    }

    @Override
    public ISauce CreateSauce() {
        return new MarinaraSauce();
    }

    @Override
    public ICheese CreateCheese() {
        return new Mozzarella();
    }

    @Override
    public IVeggies[] CreateVeggies() {
        IVeggies []v={new Garlic(), new Spinach(), new EggPlant()};
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
        return "bucuresti";
    }
}
