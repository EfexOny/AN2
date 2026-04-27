#include <iostream>

using namespace std;

int M=5;
int w[] = {1,2,3,4};
int n=4;
int x[4]={0};


void submultimiOpt(int s,int k,int r){
    


    x[k]=1;
    if(s+w[k]==M){
        for(int i=0;i<n;i++)
            cout<<x[i]<<" ";
        cout<<endl;

    }
    else if(s+w[k]+w[k+1] <= M){
            submultimiOpt(s+w[k],k+1,r-w[k]);
    }

    x[k]=0;
    if((s+r-w[k] >= M) && (s+w[k+1] <= M)){
        submultimiOpt(s,k+1,r-w[k]);
    }

}

int main(){

    int suma=0;
    for(int i=0;i<n;i++){
        suma+=w[i];
    }

    submultimiOpt(0,0,suma);
    return 0;
}

/*

1=4
2=8
3=16
4=
*/