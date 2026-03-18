
import os
import sys
import sqlite3

from PySide6.QtUiTools import QUiLoader
from PySide6.QtCore import QFile
from PySide6.QtWidgets import QApplication, QPushButton, QLineEdit,QTextBrowser,QStatusBar

def ceva(text):
    print(text)


def main():

    def filterOdd(list):
        return 

    def filterPrimes(list):
        return list

    
    def sumNumbers(list):
        return list
    
    def setText(textToSet):
        rezultat.setText(textToSet)

        
    loader = QUiLoader()
    app = QApplication(sys.argv)

    ui_file = QFile("app.ui")
    ui_file.open(QFile.ReadOnly)
    window = loader.load(ui_file)
    ui_file.close()

    addButton = window.findChild(QPushButton, "butonAdd")
    filterOddButton = window.findChild(QPushButton, "filterOdd")
    filterPrimesButton = window.findChild(QPushButton, "filterPrimes")
    sumNumberButton = window.findChild(QPushButton, "sumNumber")

    baraAdd = window.findChild(QLineEdit, "numereAdd")
    rezultat = window.findChild(QTextBrowser,"rezultatLista")

    filterOddButton.clicked.connect(filterOdd)
    filterPrimesButton.clicked.connect(filterPrimes)
    sumNumberButton.clicked.connect(sumNumbers)



    window.show()
    app.exec()


if __name__ == "__main__":
    main()

