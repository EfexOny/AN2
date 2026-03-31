package Pizza;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.IPizzaIngredientFactory;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Veggies.IVeggies;
import IngredientFactory.Sauce.*;
public abstract class Pizza {
    protected String name;
    protected ICheese cheese;
    protected IClams clams;
    protected IDough dough;
    protected IPepperoni pepperoni;
    protected ISauce sauce;
    protected IVeggies[] veggies;
    protected String processRecord;
    protected IPizzaIngredientFactory ingredientFactory;
    protected int pret;
    protected String size;
    public Pizza()
    {
        name="n1";
        processRecord="r1";
    }
    public String GetName()
    {
        return name;
    }
    public String getProcessRecord()
    {
        return processRecord;
    }
    abstract public void Prepare();
    public void Bake()
    {
        System.out.println("\nam copt pizza");
    }
    public void Cut(){
        System.out.println("\nam taiat pizza");
    }
    public void Box()
    {
        System.out.println("\nam impachetat pizza");
    }
    @Override
    abstract public String toString();

}
