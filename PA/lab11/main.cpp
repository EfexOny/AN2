/*
    Daca ai cicluri - nu se poate aplica BFS

    Lista de adiacenta
        0->1,2,3
        1->3
        2->
        3->2
    Vector
    index            0 1 2 3
    predecesori      0 1 2 2

    Implementare cu coada:
        Se adauga nodurile cu 0 predec. {Ex: 0} Daca sunt 2 noduri cu 0 predec se pun amandoi in ordine
        crescatoare   
    Afisare: se extrage 0 si se afis {0}
            Dispare nodul 0 si se actualizeaza nodurile

*/

#include <iostream>
#include <list>
#include <vector>
#include <queue>

using namespace std;

void sortareTopologica(vector <list<int>> D,int np []){
    queue <int> C;
    list <int> L;

    //insert varfuri fara predecesori
    for(int u=0;u < D.size();u++){
        if(np[u]==0){
            C.push(u);
        }
    }

    //lista varfuri
    for(int k=0;k < D.size();k++){
        if(C.empty()){
            return;
        }
        //extrage elem
        int u=C.front();
        C.pop();

        //inserezi la inceput in lista
        L.push_back(u);

        list<int> p = D[u];
    
        while(!p.empty()){
            int v = p.front();

            np[v] -=1;

            if(np[v]==0){       
                C.push(v);
            }
            p.pop_front();
        }
    }

    for(int x : L){
        cout<<x<<" ";
    }

    cout << endl;
}

int main(){
    vector<list<int>> D(5);
    D[0] = {1, 3};
    D[1] = {2,4};
    D[2] = {4};
    D[3] = {1,4};
    D[4] = {};

    int np [] = {0, 2, 1, 1, 3}; // vectorul de predecesori precalculat

    sortareTopologica(D, np);
    return 0;
}