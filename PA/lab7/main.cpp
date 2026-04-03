#include <iostream> 

using namespace std;

struct element {
    int greutate;
    int profit;
};  

struct lista {
    element *data;
    lista *next;
};


/*
M greutatea maxima
n nr elem

w = {3,5,6}
p = {10,30,20}

s={(0,0)}
t={(3,10)}

iteratia 0 :
    s1={(0,0),(3,10)}
    t1={(5,30),(8,40)}

iteratia 1:
    s2={(0,0),(3,10),(5,30),(8,40)} 
    t2={(6,20),(9,30),(11,50),(14,60)} -> elem viit + elem din s2

iteratia 2:
    s3={(0,0),(3,10),(5,30),(8,40),(6,20)}
    t3={}


*/

void afisare(lista *l){
    lista *parc=l;
    while(parc){
        cout << "(" << parc->data->greutate << ", " << parc->data->profit << ") ";
        parc=parc->next;
    }
    cout << endl;
}

lista* interclasare(lista *S, lista *T,int greutate){
    lista *ret = new lista{nullptr,nullptr};
    ret->next=nullptr;   
    lista *coada = ret; 

    lista *parcS = S;
    lista *parcT = T;

    // in s ai elementele in t aduni 

    while(parcS && parcT){

        lista *add1=new lista{new element{parcS->data->greutate,parcS->data->profit},nullptr};
        lista *add2=new lista{new element{parcT->data->greutate,parcT->data->profit},nullptr};

        if(add1->data->greutate <= add2->data->greutate){

            // verifici si profitabilitatea

            coada->next=add1;   
            coada=coada->next;
            parcS=parcS->next;
        }else{
            coada->next=add2;
            coada=coada->next;
            parcT=parcT->next;
        }

    }

    // cand termini una din liste pui in continuare;

    while(parcS){
        lista *add1=new lista{new element{parcS->data->greutate,parcS->data->profit},nullptr};
        coada->next=add1;
        coada=coada->next;
        parcS=parcS->next;
    }

    while(parcT){
        lista *add2=new lista{new element{parcT->data->greutate,parcT->data->profit},nullptr};
        coada->next=add2;
        coada=coada->next;
        parcT=parcT->next;
    }

    lista *prev=ret;
    lista *parcRet=ret->next;

    int max=-1;
    while(parcRet){   
        
        if(parcRet->data->greutate > greutate  ||  parcRet->data->profit <= max){
            prev->next=parcRet->next;
            parcRet=prev->next; 
        }else{
            max=parcRet->data->profit;
            prev=parcRet;
            parcRet=parcRet->next;
        }

    }
    
    return ret->next;
}

bool exista(lista *l, int greutate, int profit) {
    lista *parc = l;
    while (parc) {
        if (parc->data->greutate == greutate && parc->data->profit == profit)
            return true;
        parc = parc->next;
    }
    return false;
}



void rucscac(int M,int n,int w[],int p[],int x[]){
    lista **S = new lista*[n];
    lista **T = new lista*[n];




    S[0] = new lista{new element{0, 0}, nullptr};
    T[0] = new lista{new element{w[0],p[0]},nullptr};

    for(int i=1;i<n;i++){


        S[i] = interclasare(S[i-1],T[i-1],M);

        afisare(S[i]);

        element *scos = new element{w[i],p[i]};
        
        T[i]=nullptr;
        lista *parc=S[i];
        while(parc){
            
            element *e_jamaica=new element;
            e_jamaica -> greutate = scos -> greutate + parc->data->greutate;
            e_jamaica -> profit   = scos -> profit   + parc->data->profit;
            
            lista *add = new lista{e_jamaica,nullptr};
            
            if(T[i]==nullptr){
                T[i]=add;
            }else {
                lista *parcT=T[i];
                while(parcT->next){
                    parcT=parcT->next;
                }
                parcT->next=add;
            }
            parc=parc->next;
        }
        cout<<endl<<"Iteratia:"<<i<<endl;
        afisare(S[i]);
        afisare(T[i]);   
    }

    lista *final = interclasare(S[n-1], T[n-1], M);
    cout<<endl<<"Rezultat final"<<endl;
    afisare(final);

    // cout<<" "<<endl;
    // for(int i=0;i<n;i++){
    //     afisare(S[i]);
    //     afisare(T[i]);
    // }
    // cout<<"FINAL:"<<endl;
    // afisare(final);

    lista *parc = final;
    while (parc->next) parc = parc->next;
    int greutateProv = parc->data->greutate;
    int profitProv = parc->data->profit;

    cout << "Profit maxim: " << profitProv << endl;
    for (int i = n-1; i >= 0; i--) {
        if (exista(S[i], greutateProv, profitProv)) {
            x[i] = 0;  // nu am luat obiectul i
        } else {
            x[i] = 1;  // am luat obiectul i
            greutateProv -= w[i];
            profitProv -= p[i];
        }
    }

    for(int i=0;i<n;i++){
        cout<<x[i]<<" ";
    }
    
}







int main(){ 
    int w[]={3,5,6};
    int p[]={10,30,20};
    int x[]={};
    rucscac(10,3,w,p,x);
    
    

}   