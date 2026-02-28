package org.example;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;

import java.awt.*;
import java.util.Set;

public class Main {

    public static class nod {
        String cuv;
        nod urm;
    }

    public static class lista{
        nod inceput,sfarsit;
    }

    private static String RToUpper(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("python", "'" + token + "'.upper()");
        String resultString = result.asString();
        polyglot.close();

        return resultString;
    }

    private static int SumCRC(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value pythonLogic = polyglot.eval("python", "lambda s: sum(ord(ch) for ch in s[1:-1])");
        int val=pythonLogic.execute(token).asInt();
        polyglot.close();
        return val;
    }

    private static Value genereazaListaPY(Context polyglot){
        String genereaza=
                "import random\n" +
                "lst = [random.randint(0,10) for x in range(20)]\n"+
                "lst";


        return polyglot.eval("python",genereaza);
    }


    private static void AfiseazaListaJS(Context polyglot,Value vec) {
        String js =
                "(function(arr) {\n" +
                    "for(let i = 0; i < arr.length ;i++) {\n" +
                         "console.log(arr[i]);\n" +
                    "}\n" +
                "})";
        polyglot.eval("js",js).execute(vec);
    }

    private static Value sortAndRemovePY(Context polyglot,Value vec){
        String Py =
                "lambda s : sorted(s)[ int(len(s)*0.2) : int(len(s)) - int(len(s) * 0.2) ]";
        return polyglot.eval("python",Py).execute(vec);
    }

    private static Value mediaVectorPY(Context polyglot,Value vec){
        String py =
                "lambda s : sum(s) / len(s) ";
        return polyglot.eval("python",py).execute(vec);
    }

    public static void main(String[] args) {
        Context polyglot = Context.newBuilder("python", "js").allowAllAccess(true).build();

        lista[] sumaControl = new lista[900];

        for(int i=0;i<900;i++){
            sumaControl[i]=new lista();
            sumaControl[i].inceput=null;
            sumaControl[i].sfarsit=null;
        }

        Value array = polyglot.eval("js", "[\"If\",\"we\",\"run\",\"the\",\"java\",\"command\",\"included\",\"in\",\"GraalVM\",\"we\",\"will\",\"be\",\"automatically\",\"using\",\"the\",\"Graal\",\"JIT\",\"compiler\",\"no\",\"extra\",\"configuration\",\"is\",\"needed\",\"I\",\"will\",\"use\",\"the\",\"time\",\"command\",\"to\",\"get\",\"the\",\"real\",\"wall\",\"clock\",\"elapsed\",\"time\",\"it\",\"takes\",\"to\",\"run\",\"the\",\"entire\",\"program\",\"from\",\"start\",\"to\",\"finish\",\"rather\",\"than\",\"setting\",\"up\",\"a\",\"complicated\",\"micro\",\"benchmark\",\"and\",\"I\",\"will\",\"use\",\"a\",\"large\",\"input\",\"so\",\"that\",\"we\",\"arent\",\"quibbling\",\"about\",\"a\",\"few\",\"seconds\",\"here\",\"or\",\"there\",\"The\",\"large.txt\",\"file\",\"is\",\"150\",\"MB\"];");
        for (int i = 0; i < array.getArraySize();i++){
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);

            nod nou=new nod();
            nou.cuv=element;
            nou.urm=null;

            if(sumaControl[crc].sfarsit==null){
                sumaControl[crc].inceput=sumaControl[crc].sfarsit=nou;
            }else{
                sumaControl[crc].sfarsit.urm=nou;
                sumaControl[crc].sfarsit=nou;
            }
        }

//        EX LAB
        Value listaGenerata = genereazaListaPY(polyglot);
        AfiseazaListaJS(polyglot,listaGenerata);
        System.out.println("LISTA SORTATA:\n");
        Value listaSortata = sortAndRemovePY(polyglot,listaGenerata);
        AfiseazaListaJS(polyglot,listaSortata);
        System.out.println("media= " +mediaVectorPY(polyglot,listaSortata).asDouble());
//


        for(int i=0;i<900;i++){
            if(sumaControl[i].inceput !=null){
                System.out.println("\n i: "+ i +" cuvinte= ");
                nod parc= sumaControl[i].inceput;
                while(parc!=null){
                    System.out.print(parc.cuv + " ");
                    parc=parc.urm;
                }
            }
        }


        polyglot.close();
    }
}