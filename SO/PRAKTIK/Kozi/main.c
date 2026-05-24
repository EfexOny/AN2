/*
    
*/

#include "hdr.h"
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/wait.h>

#define KEY 6942

struct msg{
    long mType;
    char mesText[255];
};

void trimit_si_ya(void){
    struct msg mesaj_trimis;
    int msgId_trimis;

    msgId_trimis = msgget((key_t)KEY, IPC_CREAT | 0666);

    if(msgId_trimis == -1){
        err_sys("ue bomba amarandei");
        exit(EXIT_FAILURE);
    }

    /*
    type-ul este arbitrar, poti pune orice
    in reciever daca pui type 0,iei orice dincoad
    */
    mesaj_trimis.mType = 1;
    strncpy(mesaj_trimis.mesText, "marius, telefonul meu e la tine?",sizeof(mesaj_trimis.mesText) - 1);

    /*
        msgsnd face o coada dak nu e creata si altfel pune peste
        poti sa vezi ce argumente asteapta ca te uiti pe functie
    */
    if( (msgsnd(msgId_trimis,&mesaj_trimis,strlen(mesaj_trimis.mesText)+1,0) == -1 )){ 
        err_sys("amarandei, a dat eroare");
        exit(EXIT_FAILURE);
    }

    printf("{AMARANDEI} Am trimis boss mesajul, da-mi o bere.\n");


    pid_t copil = fork();

    if(copil == -1){
        err_sys("eroare la copiii");
        exit(EXIT_FAILURE);
    }

    if(copil==0){ // executie pentru copii
        // logica reciever 
    
        int msqid = msgget((key_t)KEY, IPC_CREAT | 0666); // scoatem id-ul cozii
        if (msqid == -1) {
            perror("msgget error");
            exit(EXIT_FAILURE);
        }

    ssize_t bytes_read;

    bytes_read = msgrcv(msqid, &mesaj_trimis, sizeof(mesaj_trimis.mesText), 0, 0);

    if(bytes_read == -1){
        err_sys("da, ai eroare pula");
        exit(EXIT_FAILURE);
    }

    printf("[AMARANDEI] Mesaj primit: %s\n", mesaj_trimis.mesText);
    } else { // executie parinte
    
        // citesti mesaj + wait pan' primeste copilu mesaj
        wait(NULL);

        // stergi ce e in coada
        if(msgctl(msgId_trimis,IPC_RMID,NULL)==-1){ 
            err_sys("Am vrut s-o sterg,amarandei,dar nu a mers");
            exit(EXIT_FAILURE);
        }
    }
}


int main(void){

    trimit_si_ya();

    return 0;
}




