#include <iostream>
using namespace std;

struct element{
    int data; 
    element *urm,*pred;
};

struct lista{
    element *primu,*ultimu;
};


void AddLista(lista *&l,int data){
   if(l->primu==0)
    {
      
        element *nou=new element;
        nou->data=data;
        nou->urm=0;
        nou->pred=0;
        l->primu=nou;
        l->ultimu=l->primu;
    }
    else
    {
        element *p=l->primu;
        while(p->urm!=0)
        {
            p=p->urm;
        }
        element *nou = new element;
        nou->data=data;
        nou->pred=p;
        nou->urm=0;
        p->urm=nou;
    }
}
void addListaPoz(lista *&l,int poz,int data){
    if(l->primu==0)
    {
        element *nou=new element;
        nou->data=data;
        nou->urm=0;
        nou->pred=0;
        l->primu=nou;
        l->ultimu=l->primu;
    }
    else
    {
        element *parc=new element;
        parc=l->primu;
        while(poz-1)
        {
            parc=parc->urm;
            poz--;
        }
       element *nou = new element;
        nou->data=data;
        nou->pred=parc;
        nou->urm=parc->urm;
        parc->urm=nou;
    }
}


 void afisare(lista *l)
{
    if(l->primu!=0)
    {
        element *p=l->primu;
        while(p->urm!=0)
        {
            cout<<p->data<<" ";
            p=p->urm;
        }
        cout<<p->data<<" ";
    }

}

int main()
{   
    // lista *l;
    // l->primu=0;
    // l->ultimu=0;
    // AddLista(l,2);
    // AddLista(l,2);
    // AddLista(l,2);
    // addListaPoz(l,2,50);
    // afisare(l);

    int a[3][3];
    int cont=1;
    for(int i=0;i<=2;i++)
    {
        for(int j=0;j<=2;j++)
        {
            a[i][j]=cont;
            cont++;
        }
    }


    for(int i=0;i<=2;i++)
    {
        for(int j=i+1;j<=2;j++)
        {
            swap(a[i][j],a[j][i]);
        }
    }

    int n=3;
    int temp;

    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n/2;j++)
        {
            temp = a[i][j];
            a[i][j]=a[i][n-j-1];
            a[i][n-j-1]=temp;

        }
    }
    
    for(int i=0;i<=2;i++)
    {
        for(int j=0;j<=2;j++)
        {
            cout<<a[i][j]<<" ";
        }
        cout<<endl;
    }


    return 0;
}