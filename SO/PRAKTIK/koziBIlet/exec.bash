#!/bin/sh

if [ $# -eq 1 ]; then
	if [ "$1" = "compilare" ]; then
		gcc main.c err.c -o main
	fi

elif [ $# -eq 2 ]; then
	if [ "$1" = "rulare" ]; then
		./main $2
	fi
fi