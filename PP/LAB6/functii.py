import os
import struct
from clase import BMP, XMLFile, TextUNICODE, TextASCII

def walk(path):
    # Liste pentru a pastra fisierele gasite
    fisiere_xml = []
    fisiere_unicode = []
    fisiere_bmp = []

    # Parcurgere recursiva a directorului primit ca parametru
    for root, subdirs, files in os.walk(path):
        for file in files:
            file_path = os.path.join(root, file)
            
            if os.path.isfile(file_path):
                # Deschidem binar ('rb') ca sa citim bytes
                f = open(file_path, 'rb')
                try:
                    content = f.read()
                    
                    # 1. Este BMP? (incepe cu BM)
                    if content.startswith(b'BM'):
                        # Extragem latime, inaltime, bpp de la offset-urile specifice BMP
                        width = struct.unpack('<i', content[18:22])[0]
                        height = struct.unpack('<i', content[22:26])[0]
                        bpp = struct.unpack('<h', content[28:30])[0]
                        
                        fisiere_bmp.append(BMP(file_path, None, width, height, bpp))
                        
                    # 2. Este UNICODE? (verificam BOM - Byte Order Mark pt UTF-8, UTF-16 LE/BE)
                    elif content.startswith(b'\xef\xbb\xbf') or content.startswith(b'\xff\xfe') or content.startswith(b'\xfe\xff'):
                        fisiere_unicode.append(TextUNICODE(file_path, None))
                        
                    # 3. Este XML ASCII? (incepe cu <?xml si contine doar caractere ASCII)
                    elif content.startswith(b'<?xml'):
                        if all(b < 128 for b in content): # Doar ASCII
                            fisiere_xml.append(XMLFile(file_path, None, "xml"))
                            
                finally:
                    f.close()

    # --- Afisare finale conform cerintei ---
    print("--- Fisiere XML ASCII ---")
    for f in fisiere_xml:
        print(f.path_absolut)

    print("\n--- Fisiere UNICODE ---")
    for f in fisiere_unicode:
        print(f.path_absolut)

    print("\n--- Fisiere BMP ---")
    for b in fisiere_bmp:
        print(f"{b.path_absolut} -> {b.width}x{b.height}, bits per pixel: {b.bpp}")
