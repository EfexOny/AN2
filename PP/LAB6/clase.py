from abc import ABC, abstractmethod

class GenericFile(ABC):

    @abstractmethod
    def get_path(self):
        pass

    @abstractmethod
    def get_freq(self):
        pass

class TextASCII(GenericFile):
    def __init__(self,path_absolut,frecvente):
        self.path_absolut=path_absolut
        self.frecvente=frecvente

    def get_path(self):
        print("ascii path")


    def get_freq(self):
        print("ascii freq")

class XMLFile(TextASCII):
    def __init__(self, path_absolut, frecvente,first_tag):
        super().__init__(path_absolut, frecvente)
        self.first_tag=first_tag

    def get_first_tag(self):
        pass


class TextUNICODE(GenericFile):
    def __init__(self,path_absolut,frecvente):
        self.path_absolut=path_absolut
        self.frecvente=frecvente

    def get_path(self):
        print("unicode path")


    def get_freq(self):
        print("unicode freq")

class Binary(GenericFile):
    
    def __init__(self,path_absolut,frecvente):
        self.path_absolut=path_absolut
        self.frecvente=frecvente

    def get_path(self):
        print("binary path")


    def get_freq(self):
        print("binary freq")

class BMP(Binary):
    def __init__(self, path_absolut, frecvente,width,height,bpp):
        super().__init__(path_absolut, frecvente)
        self.width = width
        self.height = height
        self.bpp = bpp

    def show_info():
        pass


