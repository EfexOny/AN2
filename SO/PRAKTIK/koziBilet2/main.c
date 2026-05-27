
#include "hdr.h"
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define KEY 849

struct mesaj {
    long m_type;
    char m_text[256];
};

int main(void){

    int coada_id = msgget((key_t)KEY, IPC_CREAT | 0666 );
    char decizie;

    scanf(" %c",&decizie);

    pid_t copil1 = fork(); // creare copil 1

    pid_t copil2;

    if(copil1==-1){
        err_sys("eroare copil1");
        exit(EXIT_FAILURE); 
    }else if(copil1==0){
        struct mesaj mesaj_copil1;

        if(decizie == 's'){
            if(msgrcv(coada_id,&mesaj_copil1,sizeof(mesaj_copil1.m_text),2,0) ==-1){
                err_sys("eroare copil1");
                exit(EXIT_FAILURE); 
            }
            printf("%s\n",mesaj_copil1.m_text);
        }else if(decizie == 'S'){
            if(msgrcv(coada_id,&mesaj_copil1,sizeof(mesaj_copil1.m_text),1,0)==-1){
                err_sys("eroare copil1");
                exit(EXIT_FAILURE); 
            }
            printf("%d, %s\n",getppid(),mesaj_copil1.m_text);

        }
        exit(EXIT_SUCCESS);
    }
 
    copil2 = fork();

    if(copil2==-1){
        err_sys("eroare copil1");
        exit(EXIT_FAILURE); 
    }else if(copil2==0){
        struct mesaj mesaj_copil2;
        if(decizie == 's'){
            if(msgrcv(coada_id,&mesaj_copil2,sizeof(mesaj_copil2.m_text),1,0) ==-1){
                err_sys("eroare copil2");
                exit(EXIT_FAILURE); 
            }
        printf("%d, %s\n",getppid(),mesaj_copil2.m_text);

        }else if(decizie == 'S'){
            if(msgrcv(coada_id,&mesaj_copil2,sizeof(mesaj_copil2.m_text),2,0)==-1){
                err_sys("eroare copil1");
                exit(EXIT_FAILURE); 
            }

        printf("%s\n",mesaj_copil2.m_text);
        }

        exit(EXIT_SUCCESS);
    }
    

    

    struct mesaj m1;
    struct mesaj m2;



    if(decizie == 's'){
        strcpy(m1.m_text,"mesaj tip 2, copil 1");
        strcpy(m2.m_text,"mesaj tip 1, copil 2");    

        m1.m_type=2;
        m2.m_type=1;

    }else if(decizie == 'S'){
        strcpy(m1.m_text,"mesaj tip 1, copil 2");
        strcpy(m2.m_text,"mesaj tip 2, copil 1");    

        m1.m_type=1;
        m2.m_type=2;
    }

    if(msgsnd(coada_id,&m1,sizeof(m1.m_text),0)==-1){
        err_sys("eroare send m1");
        exit(EXIT_FAILURE);
    }
    if(msgsnd(coada_id,&m2,sizeof(m2.m_text),0)==-1){
        err_sys("eroare send m1");
        exit(EXIT_FAILURE);
    }



    int status1,status2;
    waitpid(copil1,&status1,0);
    waitpid(copil2,&status2,0);
    msgctl(coada_id,IPC_RMID,0);

    printf("%d,%d\n",WEXITSTATUS(status1),WEXITSTATUS(status2));

    // msgctl

    return 0;
}