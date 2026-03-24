
vector = list[int]

def scale(scalar: float, vector: vector) -> vector:
    return [scalar * num for num in vector]

def main():
    v =[1.0, -4.2, 5.4]
    u=scale(0.5,v)
    print(u)

if __name__ == "__name__":
    main()

