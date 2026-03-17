# instructiuni:
# 1. install uv
# 2. (in folderul de proiect): uv init
# 3. uv add pyside6
# 4. se face app.ui cu qt designer (uv run pyside6-designer) -> salvam ca "app.ui"
# 4. (in vscode cu extensia python): 

import os
import sys
import sqlite3

from PySide6.QtUiTools import QUiLoader
from PySide6.QtCore import QFile
from PySide6.QtWidgets import QApplication, QPushButton


def searchButton_clicked():
    print("click!!!")


def main():

    loader = QUiLoader()
    app = QApplication(sys.argv)

    ui_file = QFile("app.ui")
    ui_file.open(QFile.ReadOnly)
    window = loader.load(ui_file)
    ui_file.close()

    # work with widgets

    # find button by name (given from designer)
    button = window.findChild(QPushButton, "searchButton")
    button.clicked.connect(searchButton_clicked)

    # show window and start application
    window.show()
    app.exec()


if __name__ == "__main__":
    main()
