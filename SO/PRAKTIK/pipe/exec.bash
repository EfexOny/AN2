
if [ $# -ne 1 ]; then 
	echo "usage: ./exec.bash [rulare/compilare]"
	exit 1
fi 

if [ "$1" = "rulare" ]; then
	if ! [ -f "main" ]; then
		echo "nu exista executabilul"
		exit 1
	fi
	./main
fi

if [ "$1" = "compilare" ]; then
	gcc main.c -o main
fi
