#include <iostream>
#include <math.h>


struct lista{
    int data;
    lista *urm;
};

struct pachet{
    lista *primul,*ultimul;
};

void inserare(lista* &cap, int valoare) {
    lista* nou = new lista;
    nou->data = valoare;
    nou->urm = nullptr;

    if (cap == nullptr) {
        cap = nou;
        return;
    }

    lista* temp = cap;
    while (temp->urm != nullptr) {
        temp = temp->urm;
    }
    temp->urm = nou;
}


void stergere(lista* &cap) {
    if (cap == nullptr) {
        return; 
    }

    lista* temp = cap;
    cap = cap->urm;
    delete temp;
}

void afisare(lista* cap) {
    lista* temp = cap;
    while (temp != nullptr) {
        std::cout << temp->data << " -> ";
        temp = temp->urm;
    }
    std::cout << "NULL" << std::endl;
}

int top(lista* cap) {
    if (cap != nullptr) {
            return cap->data;
    }
    return -200;
}

int main(){

    
    return 0;
}