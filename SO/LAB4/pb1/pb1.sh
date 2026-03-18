if [[ ! $1 =~ ^[a-zA-Z0-9_-]+$ ]]
then
    echo "nu e un nume valid"
    exit 1
fi

test(){
if [ $? -ne 0 ]
then
    echo "nu a mers komanda"
else
    echo "comanda rulata cu succes"
fi
}

mkdir $1 
cd $1
touch "$1.c"
touch "$1.h"
touch "build.sh"
mkdir bin
mkdir obj
mkdir src
mkdir inc
cd src
touch "$1_src.c"
cd ..
cd inc
touch "$1_inc.h"


test

