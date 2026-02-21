/*
    FILO First In Last Out

struct stiva
{
    stiva next;
}
 */

public class Stiva {
    public static void main(String[] args){
        Stiva stack=new Stiva();

        stack.push("da");
        stack.push("nu");
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.top());
    }
    public static class Element{
        String data;
        Element next;
        public Element(String data){
            this.data=data;
        }
    }

    Element varf;

    public Stiva(){
        varf=null;

    }

    public void push(String data){
        Element insert = new Element(data);
        insert.next=varf;
        varf=insert;
    }

    public boolean IsEmpty(){
        return varf == null;
    }


    public void pop(){
        if(!IsEmpty()) {
            varf=varf.next;
        }

    }

    public String top(){
        if(!IsEmpty()) {
            return varf.data;
        }
        else{
            return null;
        }
    }
}
