#include <iostream>

using namespace std;

// arbore

struct arbore{
    int data;
    arbore *stg,*dr;
};



// lista de arbori

struct lista{
    lista *next;
    arbore *arb;
};

void addLista(lista *&curr,arbore *toInsert){

    lista *nou=new lista;
    nou->arb=toInsert;
    nou->next=nullptr;

    if(curr==nullptr){
        curr=nou;
    }else{  
        lista *parc=curr;
        while(parc->next){
            parc=parc->next;
        }
        parc->next=nou;
    }
}

arbore* removeLowest(lista *&list){
    lista *prev=nullptr;
    lista *prevLowest=nullptr;

    if(!list){
        return nullptr;
    }

    
    arbore *lowest=list->arb; 
    lista *parc= list;

    while(parc){


        if(parc->arb->data<lowest->data){
            lowest=parc->arb;
            prevLowest=prev;
        }

        prev=parc;
        parc=parc->next;
    }

    if(prevLowest==nullptr){
        list=list->next;
    }else{
        prevLowest->next=prevLowest->next->next;
    }

    return lowest;
}

void afisareLista( lista *l){
    lista *parc;
    if(l){
        parc=l;
    }
    while(parc){
        cout<<parc->arb->data<<" ";
        parc=parc->next;
    }

    cout<<endl;
}

int nrElem(lista *l){
    int rez=0;
    while(l){
        l=l->next;
        rez++;
    }
    return rez;
}

void preordArbore(arbore *arb){
    if(!arb){
        return;
    }
    cout<<arb->data<<" ";
    preordArbore(arb->stg);
    preordArbore(arb->dr);
}

void lep(lista *& l){
    // extragi cei mai mici 2 arbori pana cand ai un singur element in lista ?
    while(nrElem(l) > 1){
        arbore *l1,*l2;
        l1=removeLowest(l);
        l2=removeLowest(l);
        // faci un arobre nou si rad->data = data la cei 2 arbori
            // stg si dr sunt cei 2 arbori - arb1-> stg arb2-> dr

        arbore *nou=new arbore;
        nou->data=l1->data+l2->data;
        nou->stg=l1;
        nou->dr=l2;


        // pui arborele nou creata in lista
        addLista(l,nou);
    }
}

// void ordonareLista(lista *toOrd){
//     lista *parc=toOrd;
//     while(parc->next){

//         parc=parc->next;
//     }
// }





int main(){

    lista *l=nullptr;
    arbore *a1,*a2,*a3,*a4,*a5;
    a1=new arbore;a2=new arbore;a3=new arbore;a4=new arbore;a5=new arbore;
    a1->data=15;a2->data=5;a3->data=3;a4->data=10;a5->data=9;

    addLista(l,a1);addLista(l,a2);addLista(l,a3);addLista(l,a4);addLista(l,a5);

    afisareLista(l);


    lep(l);

    afisareLista(l);
    preordArbore(l->arb);
    return 0;
}