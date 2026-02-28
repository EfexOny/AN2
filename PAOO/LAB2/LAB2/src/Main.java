import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

//pb1
//        String x=r.readLine();
//        int n=Integer.parseInt(x);
//        int []a=new int[n];
//
//        int elem,min;
//        for(int i=0;i<n;i++)
//        {
//            x=r.readLine();
//            elem=Integer.parseInt(x);
//            a[i]=elem;
//        }
//
//        min=a[0];
//        for(int i:a)
//        {
//            if(i<min){
//                min=i;
//            }
//        }
//        System.out.println(min);
//

        //2xvec2xmatrice2xstring

        //pb4
//        String x=r.readLine();
//        int n=Integer.parseInt(x);
//        int []a=new int[n];
//
//        int elem,min;
//        for(int i=0;i<n;i++)
//        {
//            x=r.readLine();
//            elem=Integer.parseInt(x);
//            a[i]=elem;
//        }
//
//        int ant=a[0],ok=1;
//        for(int i=1;i<n && ok==1;i++){
//            if(a[i]!=ant+1){
//                ok=0;
//            }
//                ant=a[i];
//        }
//        if(ok==1){
//            System.out.println("E crescator");
//        }else {
//            System.out.println("Nu e crescator");
//
//        }

        //pb7
//        String x=r.readLine();
//        int n=Integer.parseInt(x);
//        x=r.readLine();
//        int m=Integer.parseInt(x);
//
//        int [][] matr=new int[n][m];
//
//        int cont=1;
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                matr[i][j]=cont;
//                cont++;
//            }
//        }
//
//        int suma=0;
//        for(int j=0;j<n;j++){
//            suma+=matr[0][j];
//        }
//        for(int j=0;j<n;j++){
//            suma+=matr[n-1][j];
//        }
//        for(int j=0;j<n;j++){
//            suma+=matr[j][0];
//        }
//        for(int j=0;j<n;j++){
//            suma+=matr[n-1][j];
//        }
//        System.out.println(suma);

//pb9
//        String x=r.readLine();
//        int n=Integer.parseInt(x);
//        x=r.readLine();
//        int m=Integer.parseInt(x);
//
//        int [][] matr=new int[n][m];
//
//        x=r.readLine();
//        int nr=Integer.parseInt(x);
//        int cont=1;
//
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                matr[i][j]=cont;
//                cont++;
//            }
//        }
//
//        int apar=0;
//
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//               if(matr[i][j]==nr){
//                   apar++;
//               }
//            }
//        }
//        System.out.println("nr: "+nr+" apare de:"+apar);

        FileReader citire=new FileReader("input.txt");

        StringBuilder afisare=new StringBuilder();

        int c;
        while ((c = citire.read()) != -1)
        {
           afisare.append((char)c);
        }
        citire.close();

        System.out.println(afisare);

    }
}