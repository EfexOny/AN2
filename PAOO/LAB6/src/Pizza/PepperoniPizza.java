package Pizza;

import IngredientFactory.Cheese.Mozzarella;
import IngredientFactory.IPizzaIngredientFactory;

public class PepperoniPizza extends Pizza {
    public PepperoniPizza(IPizzaIngredientFactory ingredientFactory, String name, int pret, String size)
    {
        this.ingredientFactory=ingredientFactory;
        this.name=name;
        this.pret=pret;
        this.size=size;
    }
    @Override
    public void Prepare() {
        this.dough=ingredientFactory.CreateDough();
        this.pepperoni=ingredientFactory.CreatePepperoni();
        this.sauce=ingredientFactory.CreateSauce();
        this.veggies=ingredientFactory.CreateVeggies();
        System.out.println("\nam preparat pepperoni pizza"+sauce.GetName()+" "+veggies[0].GetName()+" "+dough.GetName()+ " "+pepperoni.GetName());
    }
    @Override
    public String toString()
    {
        return name+ " "+ ingredientFactory+" "+ size+ " "+pret+" RON";
    }
}