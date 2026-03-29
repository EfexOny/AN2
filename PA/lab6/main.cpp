/*


*/


#include <iostream>
using namespace std;

void Rucsac1(double w[], double p[], int n, int M, double x[])
{
    int S[100];
    int size = n;
    int C = 0;
    
    for(int i = 0; i < n; i++)
    {
        S[i] = i;
        x[i] = 0;
    }

    while(C < M && size > 0)
    {
        int best = 0;
        double maxPRFG = p[S[0]];

        for(int i = 1; i < size; i++)
        {
            double PRFG = p[S[i]] ;
            if(PRFG > maxPRFG)
            {
                maxPRFG = PRFG;
                best = i;
            }
        }

        int i = S[best];

        for(int j = best; j < size - 1; j++)
        {
            S[j] = S[j + 1];
        }
        size--;

        if(C + w[i] <= M)
        {
            x[i] = 1;
            C += w[i];
        }
        else
        {
            x[i] = (double)(M - C) / w[i];
            C = M;
        }
    }
}

void Rucsac2(double w[], double p[], int n, int M, double x[])
{
    int S[100];
    int size = n;
    int C = 0;
    
    for(int i = 0; i < n; i++)
    {
        S[i] = i;
        x[i] = 0;
    }

    while(C < M && size > 0)
    {
        int best = 0;
        double maxPRFG = (double)p[S[0]] / w[S[0]];

        for(int i = 1; i < size; i++)
        {
            double PRFG = (double)p[S[i]] / w[S[i]];
            if(PRFG > maxPRFG)
            {
                maxPRFG = PRFG;
                best = i;
            }
        }

        int i = S[best];

        for(int j = best; j < size - 1; j++)
        {
            S[j] = S[j + 1];
        }
        size--;

        if(C + w[i] <= M)
        {
            x[i] = 1;
            C += w[i];
        }
        else
        {
            x[i] = (double)(M - C) / w[i];
            C = M;
        }
    }
}

int main() {
    int n;
    double M;

    n=3;
    M=10;

    double w[]={2,6,5}, p[]={3,6,10}, x[n];

    
    Rucsac1(w, p, n, M, x);

    double profitTotal = 0;
    for (int i = 0; i < n; i++) {
        cout << "x[" << i << "] = " << x[i] << "\n";
        profitTotal += x[i] * p[i];
    }
    cout << "Profit total: " << profitTotal << "\n";

    return 0;
}