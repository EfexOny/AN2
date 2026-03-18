#include <iostream>

using namespace std;

struct minHeap{
    int data;
    minHeap *stg,*dr;
};

void insert(minHeap *&arbore,int data){
    if(arbore==0){
        minHeap *nou=new minHeap;
        nou->data=data;
        nou->stg=nou->dr=nullptr;
        arbore=nou;
        return;
    }
    // adaugi jos de tot si 
    minHeap *parc=arbore;
    while(parc->stg && parc->dr)
    if(data<parc->data){
        parc=parc->stg;
    }else{
        parc=parc->dr;
    }

    minHeap *nou=new minHeap;
    nou->data=data;
    nou->stg=nou->dr=nullptr;
    if(nou->data<parc->data){
        parc->stg=nou;
    }else{
        parc->dr=nou;
    }
}

int top(minHeap const *arbore){
    return arbore->data;
}

int main(){
    minHeap *arbore=0;
    insert(arbore,12);
    insert(arbore,8);
    insert(arbore,9);
    insert(arbore,10);
    insert(arbore,14);
    cout<<top(arbore)<<endl;
    return 0;
}