/*
    Singleton -
        1)constructori privati
        2)metoda publica uzual - getInstance()
                        daca exista instanta -> return this;
                                        else -> return new ...;

 */


import java.util.Vector;

abstract class AbstractFactory {
    abstract AbstractMotoare CreazaMotor();
    abstract AbstractCaroserie CreazaCaroserie();
}
class Fabrica1 extends AbstractFactory{
    AbstractMotoare CreazaMotor(){
        return new Motoare1("Motor Benzina ");
    }
    AbstractCaroserie CreazaCaroserie() {
        return new Caroserie1("Masina teren ");
    }
}
class Fabrica2 extends AbstractFactory {
    AbstractMotoare CreazaMotor() {
        return new Motoare2 ("Motor Jet ");
    }
    AbstractCaroserie CreazaCaroserie() {
        return new Caroserie2 ("Avion ");}
}
abstract class AbstractMotoare {
    public abstract void operatiaM1();
    public abstract void operatieM2();
}
class Motoare1 extends AbstractMotoare{
    Motoare1(String arg)
    {System.out.println("Model motor1: "+arg);}

    public void operatiaM1() {
        System.out.println("Monteaza");

    };
    public void operatieM2() {
        System.out.println("Ansambleaza");
    };
}
class Motoare2 extends AbstractMotoare{
    Motoare2(String arg)
    {System.out.println("Model motor2: "+arg);}
    public void operatiaM1(){
        System.out.println("Ansambleaza");
    };
    public void operatieM2() {
        System.out.println("Monteaza");
    };
}
abstract class AbstractCaroserie {
    public abstract void operatiaC1();
    public abstract void operatieC2();
}
class Caroserie1 extends AbstractCaroserie{

    Caroserie1(String arg) {
        System.out.println("Model caroserie1: "+arg);
    }
    public void operatiaC1(){
        System.out.println("Monteaza elementele");

    };
    public void operatieC2() {
        System.out.println("Vopseste elementele");
    };
}
class Caroserie2 extends AbstractCaroserie{
    Caroserie2(String arg)
    {System.out.println("Model caroserie2: "+arg);}

    public void operatiaC1() {
        System.out.println("Monteaza elementele");
    };
    public void operatieC2() {
        System.out.println("Vopseste elementele");
    };
}


class FactoryMaker{
    private static AbstractFactory pfactory=null;

//in plus
private static FactoryMaker instance = null;

//    constr privat
//    private FactoryMaker () {
//
//    }
//
//    public static FactoryMaker getInstance(){
//        if(instance != null){
//            return instance;
//        }else{
//            instance = new FactoryMaker();
//        }
//        return instance;
//    }
//
    static AbstractFactory getFactory(String opt){
        if(opt.equals("a")){
            pfactory = new Fabrica1();
        }
        else
        if(opt.equals("b")){
            pfactory = new Fabrica2();
        }
        return pfactory;
    }
}


public class Main {
    public static void main(String[] args) {

        Vector<AbstractMotoare> v1 = new Vector<>() ;
        Vector<AbstractCaroserie> v2 = new Vector<>();

        FactoryMaker fm = new FactoryMaker();

        AbstractFactory pf1 = fm.getFactory("a");
        AbstractFactory pf2 = fm.getFactory("b");

        v1.add(pf1.CreazaMotor());
        v1.add(pf2.CreazaMotor());

        v2.add(pf1.CreazaCaroserie());
        v2.add(pf2.CreazaCaroserie());

        System.out.println(v1);
        System.out.println(v2);

    }
}