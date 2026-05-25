
/*
    getppid(); - return id process parinte al procesului curent
    getpid(); - return id process curent

    parinte -> fiu -> fiu2  
    
*/

#include <stdio.h>
#include <signal.h>
#include "unistd.h"
#include "hdr.h"

int id_copil = 0;
int quit     = 0;

static void parinte_sigusr1(int semnal){

    /*
        verificam daca mai exista copilul din motive de sync
    */ 
    if(kill(id_copil,SIGUSR1) == -1){
        err_sys("desync");
        exit(EXIT_FAILURE);
    }
    // trimit mai departe catre fiu 


}

static void parinte_sigusr2_sigterm(int semnal){

     if(kill(id_copil,SIGTERM) == -1){
        err_sys("desync");
        exit(EXIT_FAILURE);
    }
    // trimit sigterm catre fiu 
    pid_t pid = getpid();
    printf("%d\n",pid);
    printf("%d\n",id_copil);
    quit = 1;

}

static void copil_sigusr1(int semnal){

    printf("%d",semnal);
    printf("%d",getppid());
}

static void copil_sigterm(int semnal){
    printf("%d",getppid());
    quit = 1;
}   

int main(void){

    int pid = fork();

    if(pid){
        // parinte
        id_copil=pid;
        pid = getpid();

        if(signal(SIGUSR1,parinte_sigusr1) == SIG_ERR){
            err_sys("eroare");
        }

        if(signal(SIGUSR2,parinte_sigusr2_sigterm) == SIG_ERR){
            err_sys("eroare");
        }

        if(signal(SIGTERM,parinte_sigusr2_sigterm) == SIG_ERR){
            err_sys("eroare");
        }

        if(signal(SIGINT,parinte_sigusr2_sigterm) == SIG_ERR){
            err_sys("eroare");
        }

        while(!quit){
            pause();
        }
    }else{
        //copil

         if(signal(SIGUSR1,copil_sigusr1) == SIG_ERR){
            err_sys("eroare");
        }

        if(signal(SIGTERM,copil_sigterm) == SIG_ERR){
            err_sys("eroare");
        }

        while(!quit){
            pause();
        }
    }
    
	return 0;
}

