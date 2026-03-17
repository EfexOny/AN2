import sqlite3
import os
from collections import namedtuple

Book = namedtuple("Book", ["id", "title", "author", "publisher"])

class DatabaseManager:
    CREATE_CMD = '''
    CREATE TABLE IF NOT EXISTS books (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(100) UNIQUE,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100))
    '''
    INSERT_CMD = '''INSERT INTO books(title, author, publisher)
    VALUES (?, ?, ?)'''
    SELECT_BY_AUTHOR_CMD = '''SELECT * FROM books WHERE author=?'''
    SELECT_BY_ID_CMD = '''SELECT * FROM books WHERE id = ?'''
    UPDATE_CMD = '''UPDATE books SET title=?, author=?, publisher=?
    WHERE id=?'''
    DELETE_ALL_CMD = '''DELETE FROM books'''
    CURRENT_PATH = os.path.dirname(os.path.abspath(__file__))
    DATABASE_PATH = os.path.join(CURRENT_PATH, 'books.db')

    def __init__(self):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.CREATE_CMD)
            cursor.close()

    def insert(self, book):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.INSERT_CMD,
            (book.title, book.author, book.publisher))
            cursor.close()
            
    def select_by_author(self, author):
        with sqlite3.connect(self.DATABASE_PATH) as db:
            cursor = db.cursor()
            cursor.execute(self.SELECT_BY_AUTHOR_CMD, (author,))
            rows = cursor.fetchall()
            cursor.close()
            return [Book(*row) for row in rows]

    