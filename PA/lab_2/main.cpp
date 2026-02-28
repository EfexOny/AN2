#include <iostream>
#include <math.h>

using namespace std;


/*
    L -  lista simpla inalntuita
    pachete: 10 (0-9 cifrele)
    baza de numeratie: 10
    m:3 - nr max de cifre

    203 14 4 13 25

    1: 

    ai un for 0 - > m for p =0;p<m;p++

    p=0
    int lastCifra = vec[i]/pow(10,p)%10

    inserare(lista,vec[i]);




    PAS 1 - cifra unitatilor

    1.1 - impachetare

    pachet[3]: 203 13
    pachet[4]: 14 4
    pachet[5]: 25

    1.2 - despachetare

    203 -> 13 -> 14 -> 4 -> 25

    PAS 2  - cifra zecilor

    2.1 - impachetare
    pachet[0]: 203 4
    pachet[1]: 13 14
    pachet[2]: 25
    
    2.2 - despachetare

    203->4->13->14->25

    PAS 3 cifra sutelor

    3.1 IMPACHETARE

    pachet[0]: 4 13 14 25
    pachet[2]: 203

    3.2 DESPACHETARE

    4 13 14 25 203

    

*/



struct lista{
    int data;
    lista *urm;
};

struct pachet{
    lista *primul,*ultimul;
};

void insert(lista *&l,int data,int where)
{
    if(l==0)
    {
        l=new lista;
        l->data=data;
        l->urm=0;
    }
    else
    {
        if(where==-1){
            lista *elem=new lista;
            elem->data=data;
            elem->urm=0;
            
            lista *p=l;
            while(p->urm!=0)
            {
                p=p->urm;
            }
            p->urm=elem;
        }
        else
        {
            lista *elem=new lista;
            elem->data=data;
            lista *p=l;
            while(p->urm!=0 && p->data!=where)
            {
                p=p->urm;
            }   
                elem->urm=p->urm;
                p->urm=elem;
        }
    }
}

void afisare(lista *l)
{
    if(l!=0)
    {
        while(l->urm!=0)
        {
            cout<<l->data<<" ";
            l=l->urm;
        }
        cout<<l->data<<" ";

    }
}

void stergere(lista *&l,int data)
{
    if(l!=0)
    {
        lista *p=l;
        if(p->data==data)
        {
            l=p->urm;
            delete(p);
        }
        else
        {
            while(p->urm!=0 && p->urm->data!=data)
            {
                p=p->urm;
            }
            lista *l=p->urm;
            p->urm=l->urm;
            delete(l);
        }
    }
}




int main(){

    lista *l=0;

    insert(l,10,-1);
    insert(l,20,-1);
    insert(l,30,-1);
    afisare(l);
    stergere(l,20);

    int m=3;


    for(int p=0;p<m;p++){
        
    }


    return 0;
}