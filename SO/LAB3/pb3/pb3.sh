#!/bin/bash

if [ $# -ne 2 ] && [ $# -ne 1 ]
then
    echo "ai nevoie de 2 operanzi sau unul"
    exit 1
fi

meniu(){
    case $1 in
    "+")
      adunare "$2" "$3"
    ;;
    "-")
      scadere "$2" "$3"
    ;;
    "*")
      inmultire "$2" "$3"
    ;;
    "/")
      impartire "$2" "$3"
    ;;
esac
}   

if [ $# -eq 2 ] 
then
  if [ -f "functii.sh" ]
    then
        source "functii.sh"
        echo "am gasit si incarcat fisierul cu ufnctii"
    else
        echo "nu am gasit fisierul cu functii"
        exit 1
  fi

  else
    echo "ibase=16; $1" | bc   
    exit 1
fi

echo "alege ce operatie sa faci, {+,-,/,*}"
read -r operatie
meniu "$operatie" "$1" "$2"


