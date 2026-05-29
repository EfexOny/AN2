
/*

Să se scrie un program în care se creează două procese în relația părinte (1) - fiu(1).
Cele doua procese citesc cate o linie de date dintr-un fişier input.txt şi vor scrie 
într-un fişier out.txt o linie de forma:,,Sunt procesul x şi am citit datele", unde x 
este PID-ul procesului. Sincronizarea se va face cu ajutorul semafoarelor. Functiile 
pentru semafoare vor fi implementate in fisierele sem.c si sem.h, iar pentru programul 
principal se va scrie un fisier main.c.


*/

#include "hdr.h"
#include <sys/ipc.h>
#include <sys/sem.h>

#define SEMPERM 0600


void semcall( int semid, int op){
    struct sembuf pbuf;
    pbuf.sem_num = 0;
    pbuf.sem_op = op;
    pbuf.sem_flg = 0;
    if ( semop( semid, &pbuf, 1) < 0)
        err_sys("Eroare semop");
}

int initsem( key_t semkey)
{
    int semid;
    semid=semget( semkey, 1, SEMPERM | IPC_CREAT);
    if ( semctl( semid, 0, SETVAL, 1) < 0)
        err_sys("Eroare semctl");
    return semid;
}

void P( int semid){
    semcall( semid, -1);
}
void V( int semid){
    semcall( semid, 1);
}


#include "hdr.h"

int main(void){

    key_t semkey=0x4244;
    int semid;
    semid = initsem(semkey);
    

    int copil = fork();
    if(copil==-1){
        err_sys("eroare la creare fiu");
        exit(EXIT_FAILURE);
    }

    

    P(semid);
    printf("sunt eu un haiduc %d\n",getpid());
    V(semid);


    if(copil==0){ // proces copil
        print_exit(EXIT_SUCCESS);
    }else { //proces parinte
        printf("Am ajuns la fsars \n");
    }

    
    return 0;
}