#include <iostream>
#include <chrono>

using namespace std;

void comparaSiSchimba(int a[],int i,int j,int s){
    if((s==0 && a[i] > a[j])  || (s==1 && a[i] < a[j] )){
        swap(a[i],a[j]);
    }
}

void sortare(int a[],int i,int d,int s){
    if(d==2){
        comparaSiSchimba(a,i,i+1,s);
    }else{
        for(int j =0;j<d/2;j++){
            comparaSiSchimba(a,i+j,i+j+d/2,s);
        }   
        sortare(a,i,d/2,s);
        sortare(a,i+d/2,d/2,s);
    }
}

void BatcherSort(int a[],int i,int d,int s){
    if(d==2){
        comparaSiSchimba(a,i,i+1,s);
    }else{
        BatcherSort(a,i,d/2,0);
        BatcherSort(a,i+d/2,d/2,1);
        sortare(a,i,d,s);
    }
}




int main(){
    int vector[16384];
    int vector2[16384];
    int vector3[16384];

    int n=16384;

    for (int i = 0; i < n; i++) {
        vector[i] = rand();
        vector2[i] = i;
        vector3[i] = n - i;
    }

    

   auto start = chrono::high_resolution_clock::now();
    BatcherSort(vector, 0, n, 0);
    auto end = chrono::high_resolution_clock::now();
    cout << "Random: " << chrono::duration_cast<chrono::microseconds>(end - start).count() << "us" << endl;


    start = chrono::high_resolution_clock::now();
    BatcherSort(vector2, 0, n, 0);
    end = chrono::high_resolution_clock::now();
    cout << "Sorted: " << chrono::duration_cast<chrono::microseconds>(end - start).count() << "us" << endl;

    start = chrono::high_resolution_clock::now();
    BatcherSort(vector3, 0, n, 0);
    end = chrono::high_resolution_clock::now();
    cout << "Reverse: " << chrono::duration_cast<chrono::microseconds>(end - start).count() << "us" << endl;


    return 0;
}