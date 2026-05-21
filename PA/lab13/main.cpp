/*
    i- nod start
    

*/
#include <iostream>

#include <iostream>
#include <cmath>
#define inf (int)INFINITY

using namespace std;


void afisare(int p[4][4], int source, int dest){
    if(p[source][dest] == source){
        cout<<(char)(source + 'A')<< "->"<< (char)(dest + 'A');
        return;
    }else{
        afisare(p, source, p[source][dest]);
        cout<<"->"<<(char)(dest + 'A');
    }
}


void Floyd_Warshall(int l[4][4],int source, int dest){

    int p[4][4];
    for(int i =0; i<4; i++){
        for (int j = 0; j < 4; j++){
            if(i != j && l[i][j] != inf){
                p[i][j] = i;
            }
            else
            p[i][j] = -1;
        }
    }


    for(int k = 0; k < 4; k++){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int temp;
                if((l[i][k] == inf) || (l[k][j] == inf)){
                     temp = inf;
                }else{
                    temp = l[i][k] + l[k][j]; 
                }

                if(temp < l[i][j]){
                    l[i][j] = temp;
                    p[i][j] = p[k][j];
                }

                if((i == j) && l[i][j] < 0){
                    cout<<"graful are circuite negative";
                    return ;
                }
            }
        }
    }
    afisare(p, source, dest);
}




int main(){
         int source, dest;
    source = 0;
    dest = 2;

    int l[4][4]={inf};

    l[0][0] = 0;
    l[0][1] = 2;
    l[0][3] = 3;
    l[1][0] = 3;
    l[1][2] = 2;
    l[2][2] = 0;
    l[2][3] = 4;
    l[3][1] = -2;
    l[3][1] = 6;
    l[3][3] = 0;



Floyd_Warshall(l, source, dest );
    
}