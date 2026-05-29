#include <sys/fcntl.h>
#include "hdr.h"


void write_to_file(char *fisier, char *mesaj){

    int fd = open(fisier,O_WRONLY | O_CREAT | O_TRUNC,0644);
    if(fd==-1){
        err_sys("EROARE write");
        exit(EXIT_FAILURE);
    }

    if( (write(fd,mesaj,strlen(mesaj)) ==-1)){
        err_sys("EROARE write pro");
        close(fd);
        exit(EXIT_FAILURE);
    }
    close(fd);
}


void read_from_file(char *fisier, char *mesaj,size_t marime){

    int fd = open(fisier,O_RDONLY);

    if(fd==-1){
        err_sys("EROARE read");
        exit(EXIT_FAILURE);
    }

    if( (read(fd,mesaj,marime)) ==-1){
        err_sys("EROARE readpr ");
        exit(EXIT_FAILURE);
    }
    close(fd);
}

int main(void){

    char mesaj_initial[64];
    fgets(mesaj_initial,25,stdin);

    write_to_file("marius.txt",mesaj_initial);

    char mesaj_primit[256];

    read_from_file("marius.txt",mesaj_primit,sizeof(mesaj_primit));

    printf("%s",mesaj_primit);




    return 0;
}