

#include <sys/sem.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/wait.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include "hdr.h"

#define KEY 1928

static void semcall( int semid, int op)
{
    struct sembuf pbuf;
    pbuf.sem_num = 0;
    pbuf.sem_op = op;
    pbuf.sem_flg = 0;
    if ( semop( semid, &pbuf, 1) < 0)
        err_sys("Eroare semop");
}
void P( int semid)
{
    struct sembuf pbuf;
    pbuf.sem_num=0;
    pbuf.sem_op=-1;
    pbuf.sem_flg=SEM_UNDO;
    semop(semid, &pbuf, 1);
}
void V( int semid)
{
    struct sembuf vbuf;
    vbuf.sem_num = 0;
    vbuf.sem_op=1;
    vbuf.sem_flg=SEM_UNDO;
    semop(semid, &vbuf, 1);
}

int initsem( key_t semkey)
{
    int semid;
    semid=semget( semkey, 1, 0600 | IPC_CREAT);
    if ( semctl( semid, 0, SETVAL, 1) < 0)
    err_sys("Eroare semctl");
    return semid;
}

int main(void){



    int semid=initsem(KEY);

    int *inc;
    
    int segment_id = shmget (IPC_PRIVATE, sizeof(int), IPC_CREAT | IPC_EXCL | S_IRUSR | S_IWUSR);

    inc = (int *) shmat(segment_id,0,0);

    pid_t copil = fork();
    if(copil==-1){
        err_sys("eroare copil1");
        print_exit(EXIT_FAILURE);
        exit(EXIT_FAILURE);
    }

    if(copil==0){
        P(semid);

        (*inc)++;

        V(semid);
        exit(EXIT_SUCCESS);
    }

    pid_t copil1 = fork();
    if(copil1==-1){
        err_sys("eroare copil1");
        print_exit(EXIT_FAILURE);
        exit(EXIT_FAILURE);
    }

    if(copil1==0){
        P(semid);

        (*inc)++;


        V(semid);
        exit(EXIT_SUCCESS);
    }


    wait(NULL);
    wait(NULL);

    printf("%d",*inc);

    semctl(semid, 0, IPC_RMID);

    return 0;
}