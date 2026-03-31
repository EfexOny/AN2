package Pizza;

import IngredientFactory.IPizzaIngredientFactory;

public class CheesePizza extends Pizza {
    public CheesePizza(IPizzaIngredientFactory ingredientFactory, String name, int pret, String size)
    {
        this.ingredientFactory=ingredientFactory;
        this.name=name;
        this.pret=pret;
        this.size=size;
    }
    @Override
    public void Prepare() {
        this.dough=ingredientFactory.CreateDough();
        this.cheese=ingredientFactory.CreateCheese();
        this.sauce=ingredientFactory.CreateSauce();
        System.out.println("\nam preparat cheese pizza "+sauce.GetName()+" "+cheese.GetName()+" "+dough.GetName());
    }
    @Override
    public String toString()
    {
        return name+ " "+ ingredientFactory+" "+ size+ " "+pret+" RON";
    }
}
