package PizzaStore;

import Pizza.Pizza;

public abstract class PizzaStore {
    protected abstract Pizza CreatePizza(PizzaType type, int pret, String size);
    public Pizza OrderPizza(PizzaType type, int pret, String size)
    {
        Pizza p= CreatePizza(type,  pret, size);
        p.Prepare();
        p.Bake();
        p.Cut();
        p.Box();
        return p;
    }
}
