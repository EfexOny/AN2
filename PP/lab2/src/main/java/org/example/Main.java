package org.example;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.example.NativePlotter;
import java.util.List;

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

        return resultString;
    }

    private static int SumCRC(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value pythonLogic = polyglot.eval("python", "lambda s: sum(ord(ch) for ch in s[1:-1])");
        int val=pythonLogic.execute(token).asInt();
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

    private static void regresieLiniaraPY(Context polyglot){

    }

    public static void main(String[] args) throws IOException {

        Context ctxJS = Context.newBuilder("js", "python").allowAllAccess(true).build();


        String venvPath = new File("target/classes/org.graalvm.python.vfs/venv/bin/graalpy").getAbsolutePath();
        Context polyglot = Context.newBuilder("python").allowAllAccess(true)
                .allowAllAccess(true)
                .allowIO(true)
                .option("python.Executable", venvPath)
                .option("python.WarnExperimentalFeatures","false")
                .allowCreateThread(true)
                .option("python.ForceImportSite", "true")
                .build();



        lista[] sumaControl = new lista[900];

        for(int i=0;i<900;i++){
            sumaControl[i]=new lista();
            sumaControl[i].inceput=null;
            sumaControl[i].sfarsit=null;
        }

        Value array = ctxJS.eval("js", "[\"If\",\"we\",\"run\",\"the\",\"java\",\"command\",\"included\",\"in\",\"GraalVM\",\"we\",\"will\",\"be\",\"automatically\",\"using\",\"the\",\"Graal\",\"JIT\",\"compiler\",\"no\",\"extra\",\"configuration\",\"is\",\"needed\",\"I\",\"will\",\"use\",\"the\",\"time\",\"command\",\"to\",\"get\",\"the\",\"real\",\"wall\",\"clock\",\"elapsed\",\"time\",\"it\",\"takes\",\"to\",\"run\",\"the\",\"entire\",\"program\",\"from\",\"start\",\"to\",\"finish\",\"rather\",\"than\",\"setting\",\"up\",\"a\",\"complicated\",\"micro\",\"benchmark\",\"and\",\"I\",\"will\",\"use\",\"a\",\"large\",\"input\",\"so\",\"that\",\"we\",\"arent\",\"quibbling\",\"about\",\"a\",\"few\",\"seconds\",\"here\",\"or\",\"there\",\"The\",\"large.txt\",\"file\",\"is\",\"150\",\"MB\"];");
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
        AfiseazaListaJS(ctxJS,listaGenerata);
        System.out.println("LISTA SORTATA:\n");
        Value listaSortata = sortAndRemovePY(polyglot,listaGenerata);
        AfiseazaListaJS(ctxJS,listaSortata);
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


        List<String> linii = Files.readAllLines(Paths.get("dataset.txt"));


        int[] x = Arrays.stream(linii.get(0).trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] y = Arrays.stream(linii.get(1).trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
//
        System.out.println();

        String regresitNumericaPY =
"""
import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
from scipy import stats
def ruleaza_regresie(x_in, y_in, nume_fisier, culoare):
    x = list(x_in)
    y = list(y_in)

    slope, intercept, r, p, std_err = stats.linregress(x, y)

    def func(val):
        return slope * val + intercept

    mymodel = list(map(func, x))

    plt.figure() 
    plt.scatter(x, y, color='blue', label='Date')
    plt.plot(x, mymodel, color=culoare, label='Magie')
    plt.legend()

    plt.savefig(nume_fisier)\s
    plt.close() 

    return float(r)
ruleaza_regresie
""";

        String fileName,cale,culoare;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("nume fisier: ");  fileName= r.readLine();
        System.out.println("cale de salvare: "); cale = r.readLine();
        System.out.println("culoare plot: ");  culoare= r.readLine();

        String fullPath = Paths.get(cale, fileName).toString();


        Value rezultat = polyglot.eval("python", regresitNumericaPY).execute(x, y, fullPath,culoare);


        Value funcRegresie = polyglot.eval("python", regresitNumericaPY);
        Value rValue = funcRegresie.execute(x, y, fullPath,culoare);
        System.out.println("rez: " + rValue.asDouble());

String citesteAruncariPY =
"""
from scipy.stats import binom

nr=int(input("nr, aruncari: "))
x=int(input("x: "))

rez = binom.cdf(x,nr,0.5)
print(rez)

""";
        Value genBinomial = polyglot.eval("python", citesteAruncariPY);
        ctxJS.close();
        polyglot.close();
    }
}