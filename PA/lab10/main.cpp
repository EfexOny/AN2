

#include <iostream>
#include <vector>
#include <queue>
#include <string>
#include <list>
#include <algorithm>

using namespace std;

//g star - euristica , probabilitatea sa fie corecta
//c* cate piese difera din config actuala cu cea finala

int DIM = 3;                     // Dimensiunea tablei

const string GOAL = "ABCEHFLGD"; // Configuratia finala Cf

vector<vector<int>> adj = {
    {1, 3},    // o piesa aflata in pozitia 0 se poate deplasa in pozitiile 1 si 3 (dreapta si jos)
    {0, 2, 4}, // o piesa aflata in pozitia 1 se poate deplasa in pozitiile 0, 2 si 4 (stanga, dreapta si jos)
    {1, 5},
    {0, 4, 6},
    {1, 3, 5, 7},
    {2, 4, 8},
    {3, 7},
    {4, 6, 8},
    {5, 7}}; // lista de adiacenta care reprezinta vecinii posibili ai fiecarei pozitii

struct Node
{                        // structura nodului pentru heap
    int level;           // nivelul in arbore
    int cStar;           // c*: nivel + g*(v)
    string config;       // configuratia curenta
    vector<string> path; // drumul parcurs pana la aceasta configuratie

    bool operator>(const Node &other) const
    {
        return cStar > other.cStar; // conditia necesara pentru coada cu prioritati
    }
};

// Functia pozC care returneaza pozitia caracterului X in configuratia C
int pozC(const string C, char X) {
    for(int i=0;i<C.length();i++){
        if(C[i] == X){
            return i;
        }
    }
}

// Numara piesele Y care apar dupa piesa X in configuratia C,
// dar care ar trebui sa apara inaintea lui X in configuratia finala (GOAL)
int lessC(const string C, char X) {
    int cont=0;int inainte=1;
    string inainte_X= "";

    for(int i=0;i<GOAL.length();i++){
        if( GOAL[i] == 'L') continue;
        if(GOAL[i]==X){
            inainte=0;
        }
        
        if(inainte==1){
            inainte_X += GOAL[i];
        }
    }

    int dupa=0;
    for(int i=0;i<C.length();i++){
        if(C[i] == X){
            dupa=1;
            continue;
        }
        if(dupa==1){
            for(int j=0;j<inainte_X.length();j++){
                if(C[i]==inainte_X[j]){
                    cont++;
                }
            }
        }
    }
    return cont;
}

// Functia C care determina paritatea pozitiei libere (L) pe tabla
int lC(const string C) {
    for(int i=0;i<C.length();i++){
        if(C[i]=='L'){
            if((i+1)%2==0){
                return 1;
            }else{
                return 0;
            }
        }
    }
}

// Functia isSolvable care decide daca configuratia initiala C poate fi rezolvata
bool isSolvable(const string C) {
    int sum=0;
    for(int i=0;i<C.length();i++){
        sum += lessC(C,C[i]);
    }

    return (sum + lC(C)) % 2 == 0;

}

// Functia gStar care calculeaza cate piese sunt in pozitie gresita fata de configuratia finala Cf
int gStar(const string C) {
    int cont =0;
    for(int i=0;i<C.length();i++){
        if(C[i] != GOAL[i]){
            cont++;
        }
    }
    return cont;
}

// Functia generateChildren care genereaza toate configuratiile posibile prin mutarea lui L
// Aici se foloseste lista de adiacenta pentru a gasi vecinii posibili lui L
vector<string> generateChildren(const string C) {
    vector<string> sol;

    int pozLiber = pozC(C,'L');

    vector<int> v = adj[pozLiber];

    for(int numar : v){
        string cop = C;

        swap(cop[pozLiber], cop[numar]); 
        sol.push_back(cop);
    }
    return sol;
}

// Functia perspico care implementeaza algoritmul Perspico
void perspico(const string radacina)
{
    // Verificam daca configuratia initiala are solutie
    if(!isSolvable(radacina)){
        return;
    }   

    // Creare min heap
    priority_queue<Node, vector<Node>, greater<Node>> A;
    list<string> vizitat;

    // Adaugam nodul de start in heap

    Node start ;
    start.config=radacina;
    start.level=0;
    start.cStar= start.level + gStar(start.config);
    start.path = {};

    A.push(start);
    vizitat.push_back(radacina);

    // tinut minte configuratii si verificat 

    while (!A.empty())
    {

        // Extragem nodul cu costul minim
        Node cur = A.top();
        A.pop();

        // Verificam daca am ajuns la solutia finala si afisam drumul parcurs pana la aceasta configuratie
        if (cur.config == GOAL) {
            for (string stare : cur.path) {
                cout << stare << "\n";
            }
            cout << cur.config << "\n";
            return; 
        }

        // Generam copii pentru nodul curent
        vector<string> copi = generateChildren(cur.config);
        

        // Adaugam fiecare copil in heap
        for(string copil : copi){

            if(find(vizitat.begin(),vizitat.end(),copil) == vizitat.end()){
                vizitat.push_back(copil);

                Node copilNod;
                copilNod.config=copil;
                copilNod.level=cur.level+1;
                copilNod.cStar= copilNod.level + gStar(copil);

                copilNod.path = cur.path;
                copilNod.path.push_back(cur.config);

                A.push(copilNod);

            }
        }
    }
}

int main()
{
    string configuratieInitiala = "ABCEFLGHD"; // configuratia initiala
    perspico(configuratieInitiala);

    // generateChildren(configuratieInitiala);
    
    return 0;
}

