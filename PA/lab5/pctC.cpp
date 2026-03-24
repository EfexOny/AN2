#include <iostream>


// arbore

struct arbore{
    int data;
    arbore *stg,*dr;
};

// lista de arbori


struct heap{
    arbore *h[100];
    int last;
};

void inserare(arbore *arb, heap *h){
    h->h[h->last] = arb;
    int curent = h->last;   
    int parinte = curent / 2;

    while(curent > 1 && h->h[curent]->data < h->h[parinte]->data){
        std::swap(h->h[curent], h->h[parinte]);
        curent = parinte;
        parinte = curent / 2;
    }
    h->last++;
}

arbore* removeMin(heap *h){
    arbore *minim = h->h[1];
    h->h[1] = h->h[h->last - 1];
    h->last--;

    int parinte = 1;
    while(true){
        int fiu1 = parinte * 2;
        int fiu2 = parinte * 2 + 1;
        int mic = parinte;

        if(fiu1 < h->last && h->h[fiu1]->data < h->h[mic]->data)
            mic = fiu1;
        if(fiu2 < h->last && h->h[fiu2]->data < h->h[mic]->data)
            mic = fiu2;

        if(mic == parinte) 
            break;  // e la locul lui

        std::swap(h->h[parinte], h->h[mic]);
        parinte = mic;
    }
    return minim;
}

void lep(heap *l){
    // extragi cei mai mici 2 arbori pana cand ai un singur element in lista ?
    while(l->last > 2){
        arbore *l1,*l2;
        l1=removeMin(l);
        l2=removeMin(l);
        // faci un arobre nou si rad->data = data la cei 2 arbori
            // stg si dr sunt cei 2 arbori - arb1-> stg arb2-> dr

        arbore *nou=new arbore;
        nou->data=l1->data+l2->data;
        nou->stg=l1;
        nou->dr=l2;


        // pui arborele nou creata in lista
        inserare(nou,l);
    }
}

void preordArbore(arbore *arb){
    if(!arb){
        return;
    }
    std::cout<<arb->data<<" ";
    preordArbore(arb->stg);
    preordArbore(arb->dr);
}

int main(){
   heap *l = new heap;
    l->last = 1;

    arbore *a1, *a2, *a3, *a4, *a5;
    a1 = new arbore; a2 = new arbore; a3 = new arbore;a4 = new arbore; a5 = new arbore;

    a1->data = 15; a1->stg = a1->dr = nullptr;
    a2->data = 5;  a2->stg = a2->dr = nullptr;
    a3->data = 3;  a3->stg = a3->dr = nullptr;
    a4->data = 10; a4->stg = a4->dr = nullptr;
    a5->data = 9;  a5->stg = a5->dr = nullptr;

    inserare(a1, l); inserare(a2, l); inserare(a3, l);
    inserare(a4, l); inserare(a5, l);

    for(int i=1;i<l->last;i++)
        std::cout<<l->h[i]->data<<" ";
    std::cout<<std::endl;


    lep(l);

    for(int i=1;i<l->last;i++)
        std::cout<<l->h[i]->data<<" ";
    std::cout<<std::endl;

    preordArbore(l->h[1]);


    return 0;
}