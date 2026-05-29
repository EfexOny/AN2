#!/bin/bash

if [ $# -eq 1 ]; then
	if [ $1 = "rulare" ]; then
		if [ -f "main" ]; then
			echo "ai dat rulare"
			./main
		fi
	elif [ $1 = "compilare" ]; then
		if [ -f "err.c" ] && [ -f "hdr.h" ] && [ -f "main.c" ]; then
			gcc main.c err.c -o main
			echo "ai compilat"
		fi
	else 
		echo "marius, usage: bash execute.bash [compilare/rulare] args"
	fi

elif [ $# -eq 2 ]; then
	echo $2
else
	echo "marius, nnu asa se foloseste"
fi