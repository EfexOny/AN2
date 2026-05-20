/*

0 ->  1 
\      \
2    <- 3

*/
#include <iostream>

#include <iostream>
#include <vector>
#include <list>
using namespace std;

#define INF 352

int n;
vector<int> D;
bool S[4];
vector<int> P;
int L[4][4];

void dijkstra(int i0){

    //punem nod de inceput in predec si punem distantele initiale
    //nu am vizitat nici un nod pana acum
    for (int i = 0; i < n; i++){
        P.push_back(i0);
        D.push_back(L[i0][i]);
        S[i] = 0;
    }

    //marcam nodul de inceput ca vizitat
    S[i0] = 1;  

    //Trecem prin toate nodurile
    //luam nodu cu cea mai mica distanta din D si nu a fost vizitat
    for(int i = 0; i < n - 1; i++){
        int k = -1;
        int minDist = INF;  
        for(int j = 0; j<n; j++){
            if(!S[j] && D[j] < minDist){
                minDist = D[j];
                k = j;
            }
        }
        if ( k == -1){
            break;
        }
        //il marcam vizitat
        S[k] = true;

        //verificam daca noul drum prin k e mai scurt decat actualu
        for ( int j = 0; j < n; j++){
            if ((!S[j] && L[k][j] != INF) && (D[k] + L[k][j] < D[j])){
                D[j] = D[k] + L[k][j];
                P[j] = k;
            }
        }
    }
}

void afisare(int i0, int d){

    if(i0 == d){
        cout<<d;
        return ;
    } else if(D[d] == INF){
        cout<<"nu exista drum de la "<< i0<< " la "<< d;
        return ;
    }
    afisare(i0, P[d]);
    cout<<"->"<< d;
}

int main(){
    n=5;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j) L[i][j] = 0;
            else L[i][j] = INF;
        }
    }

    L[0][1] = 6;
    L[0][3] = 1;

    L[1][4] = 4; 
    L[1][2] = 5; 

    L[2][4] = 5;

    L[3][4] = 1;
    L[3][1] = 2;
    
    L[4][1] = 2;


    int i0=0, d=2;
    dijkstra(i0);
    afisare(i0, d);
    cout<<endl<<"Cost:"<<D[d]<<endl;

    return 0;
}