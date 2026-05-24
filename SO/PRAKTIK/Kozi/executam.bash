
if [ $# -ne 1 ]; then
	echo "usage: bash executam.bash [compile/execute]"
	exit 1
fi

if [ "$1" = "compile" ]; then
	gcc main.c err.c -o main 
elif [ "$1" = "execute" ]; then 
	./main

else 
	echo "executa bha corect"
	exit 1

fi