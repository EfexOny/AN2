//import java.util.Vector;
//
//public class SetAsVector extends Vector<Object> {
//
//    @Override
//    public boolean add(Object a){
//        return super.add(a);
//    }
//
//    @Override
//    public boolean remove(Object a){
//        return super.remove(a);
//    }
//    @Override
//    public boolean contains(Object a){
//        return super.contains(a);
//    }
//
//    @Override
//    public String toString(){
//        return super.toString();
//    }
//    public static void main(String[] args) {
//        SetAsVector a = new SetAsVector();
//        a.add(3);
//        a.add(4);
//        a.add(5);
//        System.out.println(a.toString());
//    }
//}


import java.util.BitSet;
import java.util.Collections;
import java.util.Vector;

public class SetAsVector {
    Vector<Object> elem;
    SetAsVector(){
        elem=new Vector<>();
    }

    public boolean add(Object a){
        if(!elem.contains(a)){
            return elem.add(a);
        }else{
            return false;
        }
    }

    public boolean remove(Object a){
        return elem.remove(a);
    }

    public boolean contains(Object a){
        return elem.contains(a);
    }

    public String toStringVec(){
        return elem.toString();
    }

    public static void main(String[] args) {
        SetAsVector a = new SetAsVector();
        a.add(3);
        a.add(4);
        a.add(5);
        System.out.println(a.toStringVec());
    }
}

class sortedVector extends Vector<Object>{
    @Override
    public void addElement(Object a){
        super.addElement(a);
        Collections.sort((Vector)this);
    }
    @Override
    public void insertElementAt(Object a,int b){
        super.insertElementAt(a,b);
    }

    public static void main(String[] args) {
        sortedVector test=new sortedVector();
        test.addElement(2);
        test.addElement(10);
        System.out.println(test.toString());
        test.addElement(1);
        System.out.println(test.toString());
    }
}

class IntSet {
    BitSet vec;

    IntSet(){
        vec=new BitSet();
    }

    public void add(int nr){
        if(nr != 0){
            vec.set(nr,true);
        }
    }

    public void remove(int nr){
        if(nr!=0){
            vec.set(nr,false);
        }
    }

    public boolean contains(int nr){
        return vec.get(nr);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    public static void main(String[] args) {
        IntSet da=new IntSet();
        da.add(2);
        da.add(3);
        da.add(5);
        System.out.println(da.toString());
        System.out.println(da.contains(1));

    }
}

class Graph extends Vector<Object>{
    Vector<Object> listeAdiac;

    Graph(int nr){
        listeAdiac = new Vector<Object>(nr);
    }

    @Override
    public int size(){
        return super.size();
    }

    public void addArc(Object arc){
        super.add(arc);
    }

    public Boolean isArc(Object arc,int n1,int n2) {
        Boolean decizie=false;
        for (int i = n1; i <= n2; i++) {

        }
        return false;
    }
}
