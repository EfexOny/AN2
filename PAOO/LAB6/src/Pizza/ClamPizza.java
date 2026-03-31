package Pizza;

import IngredientFactory.IPizzaIngredientFactory;

public class ClamPizza extends Pizza {
    public ClamPizza(IPizzaIngredientFactory ingredientFactory, String name, int pret, String size)
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
        this.clams=ingredientFactory.CreateClams();
        System.out.println("\nam preparat clam pizza"+dough.GetName()+ " "+clams.GetName()+" "+cheese.GetName());
    }
    @Override
    public String toString()
    {
        return name+ " "+ ingredientFactory+" "+ size+ " "+pret+" RON";
    }
}