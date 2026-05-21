#include <iostream>

using namespace std;

#define INF 352

const int N = 5;     

void afisare(int p[N][N], int source, int dest) {
    if (p[source][dest] == source) {
        cout << source << "->" << dest;
        return;
    } else {
        afisare(p, source, p[source][dest]);
        cout << "->" << dest;
    }
}

void Floyd_Warshall(int l[N][N], int source, int dest) {
    int p[N][N];
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (i != j && l[i][j] != INF) {
                p[i][j] = i;
            } else {
                p[i][j] = -1;
            }
        }
    }

    for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp;
                if ((l[i][k] == INF) || (l[k][j] == INF)) {
                    temp = INF;
                } else {
                    temp = l[i][k] + l[k][j]; 
                }

                if (temp < l[i][j]) {
                    l[i][j] = temp;
                    p[i][j] = p[k][j];
                }

                if ((i == j) && l[i][j] < 0) {
                    cout << "Graful are circuite negative\n";
                    return;
                }
            }
        }
    }
    afisare(p, source, dest);
    cout << "\n";
}

int main() {
    int l[N][N];

    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (i == j) {
                l[i][j] = 0;
            } else {
                l[i][j] = INF;
            }
        }
    }

    l[0][1] = 6;
    l[0][3] = 1;

    l[1][4] = 4; 
    l[1][2] = 5; 

    l[2][4] = 5;

    l[3][4] = 1;
    l[3][1] = 2;
    
    l[4][1] = 2;
    
    Floyd_Warshall(l, 0, 4);
    
    return 0;
}