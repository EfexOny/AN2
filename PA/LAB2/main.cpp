#include <iostream>
#include <math.h>


struct lista{
    int data;
    lista *urm;
};

struct pachet{
    lista *primul,*ultimul;
};

/*

    elem1->elem2->nullptr

*/
//inserezi la sfarsit
void inserare(pachet *l,int data){
    lista *nou=new lista;
    nou->data=data;
    nou->urm=nullptr;
    if(l->primul==nullptr){
        l->primul=nou;
        l->ultimul=nou;
    }else{
        l->ultimul->urm=nou;
        l->ultimul=nou;
    }
}

//stergi la inceput
void sterge(pachet *l){
    lista *del=l->primul;
    l->primul=l->primul->urm;
    delete del;
}

void afisare(pachet *l){
    lista *parc=l->primul;
    while(parc->urm){
        std::cout<<parc->data<<" ";
        parc=parc->urm;
    }
    std::cout<<parc->data<<" ";
}

int main(){
    pachet *l=new pachet;
    pachet *r=new pachet[10];
    l->primul=l->ultimul=nullptr;

    inserare(l,2);
    inserare(l,42);
    inserare(l,300);
    inserare(l,239);
    inserare(l,42);
    inserare(l,55);
    inserare(l,68);
    inserare(l,32);

    int m=3;

    for(int p=0;p<m;p++){

        for(int i = 0; i < 10; i++) {
            r[i].primul = r[i].ultimul = nullptr;
        }

        while(l->primul){
            int nr=l->primul->data;
            int lastCifra = int(nr/pow(10,p))%10;
            inserare(&r[lastCifra],nr);
            sterge(l);
        }

        for(int i=0;i<10;i++){
            lista *pa=r[i].primul;
            while(pa){
                inserare(l,pa->data);
                pa=pa->urm;
            }
        }


    }

    afisare(l);
    return 0;
}
