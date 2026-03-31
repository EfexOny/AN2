
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

iteratia 1 :
    s1={(0,0),(3,10)}
    t1={(5,30),(8,40)}

iteratia 2:
    s2={(0,0),(3,10),(5,30),(8,40)} 
    t2={(6,20),(9,30),(11,50),(14,60)} -> elem viit + elem din s2

iteratia 3:
    s3={(0,0),(3,10),(5,30),(8,40),(6,20)}
    t3={}


*/

lista* interclaseare(lista *S,lista *T){
    lista *ret = new lista{};
    lista *parcS,*parcT;
    while(parcS,parcT){
        // interclasare
        
        if(ret==nullptr){
            lista *add=new lista;
                element *nou = new element;
                nou->greutate=S->data->greutate;
                nou->profit=S->data->profit;

            add->data=nou;
            add->next=nullptr;
            
            ret=add;
        }else{
            element *add=new element;
            add->greutate=S->data->greutate;
            add->profit=S->data->profit;
            
            lista *sfars=ret;
            while(sfars){
                sfars=sfars->next;
            }
            lista *adaugare=new lista;
                element *nou = new element;
                nou->greutate=S->data->greutate;
                nou->profit=S->data->profit;

            adaugare->data=nou;
            adaugare->next=nullptr;
        }


        parcS=parcS->next;parcT=parcT->next;
    }
}


void rucscac(int M,int n,int w[],int p[],int x){
    lista **S={};
    lista **T={};

    S[0] = new lista{new element{0, 0}, nullptr};
    T[0] = new lista{new element{w[0],p[0]},nullptr};

    for(int i=1;i<n;i++){
        S[i] = interclaseare(S[i-1],T[i-1]);

        element *scos = new element{w[i],p[i]};
        T[i]= new lista{scos,nullptr};

        while(nullptr){

        }
        
    }

}



int main(){ 
    int w[]={3,5,6};
    int p[]={10,30,20};
    


}