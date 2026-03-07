#include <iostream>
#include <chrono>
#include <cstdlib>

using namespace std;

void interclasare(int a[], int p, int q, int m, int temp[]) {
    int i = p;
    int j = m + 1;
    int k = -1;

    while ((i <= m) && (j <= q)) {
        k++;
        if (a[i] <= a[j]) {
            temp[k] = a[i];
            i++;
        } else {
            temp[k] = a[j];
            j++;
        }
    }
    while (i <= m) {
        k++;
        temp[k] = a[i];
        i++;
    }
    while (j <= q) {
        k++;
        temp[k] = a[j];
        j++;
    }
}

void mergeSort(int a[], int p, int q) {
    int m;
    if (p < q) {
        m = (p + q) / 2;
        mergeSort(a, p, m);
        mergeSort(a, m + 1, q);
        
        int* temp = new int[q - p + 1];
        interclasare(a, p, q, m, temp);
        
        for (int i = p; i <= q; i++) {
            a[i] = temp[i - p];
        }
        delete[] temp;
    }
}

void partitioneaza(int a[], int p, int q, int &k) {
    int x = a[p];   
    int i = p + 1;
    int j = q;
    
    while (i<=j) {
        if (a[i] <= x) i++;
        if (a[j] > x) j--;
        
        if (i < j) {
            if(a[i]>x && x>a[j]){
                swap(a[i], a[j]);
                i++;
                j--;
            }
        }
    }
    k = i-1;
    a[p]=a[k];
    a[k]=x;
}

void quicksort1(int a[], int p, int q) {
    int k;
    if (p < q) {
        partitioneaza(a, p, q, k);
        quicksort1(a, p, k - 1);
        quicksort1(a, k + 1, q);
    }
}

void quicksort2(int a[], int p, int q) {
    int k;
    while (p < q) {
        partitioneaza(a, p, q, k);
        if (k - p > q - k) {
            quicksort2(a, k + 1, q);
            q = k - 1;
        } else {
            quicksort2(a, p, k - 1);
            p = k + 1;
        }
    }
}

void afisare(int a[], int mar) {
    for (int i = 0; i < mar; i++) {
        cout << a[i] << " ";
    }
    cout << endl;
}

void randos(int a[], int dim) {
    for (int i = 0; i < dim; i++) {
        a[i] = rand();
    }
}

void copy(int a[],int b[],int dim){
     for (int i = 0; i < dim; i++) {
        b[i]=a[i];
    }
}

int main() {
    int dim = 20000;
    int a[20001];

    randos(a, dim);
    int test[20001];

    copy(a,test,dim);


    auto start = chrono::high_resolution_clock::now();
    mergeSort(a, 0, dim - 1); 
    auto end = chrono::high_resolution_clock::now();
    
    
    auto duration = (end - start);
    auto us = chrono::duration_cast<chrono::microseconds>(duration);
    float ms_fractional = static_cast<float>(us.count()) / 1000.0f;
    cout << "MergeSort Duration = " << us.count() << "µs (" << ms_fractional << "ms)" << endl;

    copy(a,test,dim);

    start = chrono::high_resolution_clock::now();
    quicksort1(a, 0, dim - 1);
    end = chrono::high_resolution_clock::now();
    
    duration = (end - start);
    us = chrono::duration_cast<chrono::microseconds>(duration);
    ms_fractional = static_cast<float>(us.count()) / 1000.0f;
    cout << "QuickSort1 Duration = " << us.count() << "µs (" << ms_fractional << "ms)" << endl;
    copy(a,test,dim);

    start = chrono::high_resolution_clock::now();
    quicksort2(a, 0, dim - 1);
    end = chrono::high_resolution_clock::now();
    
    duration = (end - start);
    us = chrono::duration_cast<chrono::microseconds>(duration);
    ms_fractional = static_cast<float>(us.count()) / 1000.0f;
    cout << "QuickSort2 Duration = " << us.count() << "µs (" << ms_fractional << "ms)" << endl;

    return 0;
}