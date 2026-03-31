import os

# 
def shit():
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))
    for root, subdirs, files in os.walk(ROOT_DIR):
        for file in os.listdir(root):
            file_path = os.path.join(root, file)
            if os.path.isfile(file_path):
                f = open(file_path, 'rb')
            try:
                content = f.read()
            finally: