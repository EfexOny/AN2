
/*
    Daca ai un pipe in care se vb un copil cu parinte
    e destul sa creezi pipe-ul inainte de fork
*/

#include "signal.h"
#include "hdr.h"
#include "err.c"

 /*
        parintele foloseste pipe-ul creat pentru a scrie mesajul
        pentru ca s-a creat forkul, al doilea proces, au acelasi fork

        0 - read 
        1 - write

        Daca folosesti read inchizi write si vice-versa
*/

int main(int argc,char *argv[]){
    
    int p[2];
    char ch;


    if(argc > 1 ){
        err_sys("Numar incorect de argm");
        exit(EXIT_FAILURE);
    }

    if(pipe(p)==-1){
        err_sys("eroare la pipe");
        exit(EXIT_FAILURE);
    }

    int nepot;

    char linie[65];
    printf("scrie linia: ");
    fgets(linie,sizeof(linie),stdin);

    int id_child = fork();
    if(id_child == -1){
        err_sys("eroare la crearecopil");
        exit(EXIT_FAILURE);
    }


    //Parinte
    if(id_child){
        close(p[0]); // inchizi partea de read

        int i=0;
        while((ch=linie[i++])){
            write(p[1],&ch,1);
        }

        close(p[1]);
    }else if(id_child == 0){ // COPIL
        char mesaj_fiu[65];

        close(p[1]);
        read(p[0],mesaj_fiu,sizeof(mesaj_fiu));
        close(p[0]);

        int p2[2];
        if(pipe(p2) == 1){
            err_sys("eroare la pipe2");
            exit(EXIT_FAILURE);
        }

        nepot=fork();
        if(nepot==-1){
             err_sys("eroare la pipe");
            exit(EXIT_FAILURE);
        }else if(nepot){ // continuarea pentru fiu 1

        close(p2[0]); // oprim partea read
        write(p2[1],mesaj_fiu,sizeof(mesaj_fiu));
        write(p2[1],"\n",1);

        close(p[1]);

    }else if (nepot == 0){//NEPOT sau FIU2
            char mesaj_nepot[65];
            close(p2[1]);
            read(p2[0],mesaj_nepot,sizeof(mesaj_nepot));
            close(p2[0]);

            printf("%s",mesaj_nepot);
            exit(EXIT_SUCCESS);
        }
    }

    return 0;
}