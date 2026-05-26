

#include <sys/signal.h>
#include <sys/file.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include "hdr.h"

#define KEY 2006

struct mesaj{
    long m_type;
    char m_text[256];
};

void alarm_handler(int alarm){
    printf("alarma\n");
}

void read_from_file(char *comanda,char *fisier){

    char buff[256];
    int fd = open(fisier,O_RDONLY);
    if(fd == -1){
        err_sys("erorae la deschide fisier");
        exit(EXIT_FAILURE);
    }
    int i=0;
    while( i < 255 && (read(fd,&buff[i],1) > 0)){
        i++;
    }

    buff[i]='\0';
    close(fd);

    strcpy(comanda,buff);
}


int main(int argc,char *argv[]){

    char comanda[256];

    char nume_fisier[64];
    strcpy(nume_fisier,argv[1]);


    read_from_file(comanda,nume_fisier);

    // printf("%s",comanda);

    signal(SIGALRM, alarm_handler);
    alarm(5);
    pause();

    int id_coada = msgget((key_t)KEY,IPC_CREAT | 0666);


    pid_t copil=fork();

    if(copil==-1){
        err_sys("eroare la creare copil");
        exit(EXIT_FAILURE);
    }

    if(copil==0){ // proces copil
        struct mesaj msg_primit;
        if(msgrcv(id_coada,&msg_primit,sizeof(msg_primit.m_text),0,0) == -1){
            err_sys("eroare la primire mesaj din coada");
            exit(EXIT_FAILURE);
        }

    
        pid_t nepot = fork();

        if(nepot ==-1){
            err_sys("eroare la nepot");
            exit(EXIT_FAILURE);
        }
        if(nepot==0){

            char *dau_cai[] = { msg_primit.m_text, NULL};
            execvp(dau_cai[0],dau_cai);

            exit(EXIT_SUCCESS);
        }

    }else if(copil > 0){ //proces parinte

        struct mesaj msg;

        strcpy(msg.m_text,comanda);
        msg.m_type=3;

        if(msgsnd(id_coada,&msg,strlen(msg.m_text)+1,0) == -1){
            err_sys("eroare la trimitere in coada");
            exit(EXIT_FAILURE);
        }

    }


    return 0;
}