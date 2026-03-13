.data
string: .string "Salut RISC-V!"

.text
.globl main

main:
    # Setarea argumentului (pointer) și apelul funcției
    la      a0, string
    jal     reverse

    # Ieșire din program
    li      a0, 10              # Cod ecall pentru exit (10) mutat în a0
    ecall

# Prototip: void reverse(char *a0)
reverse:
    # 1. Verificare caz de bază
    lb      t0, 0(a0)           
    bnez    t0, recurse         
    ret                         

recurse:
    # 2. Prolog: Salvare pe stivă
    addi    sp, sp, -16         
    sw      ra, 12(sp)          
    sw      a0, 8(sp)           

    # 3. Apel recursiv
    addi    a0, a0, 1           
    jal     reverse             

    # 4. Afișare caracter curent
    lw      t1, 8(sp)           # Restaurează adresa caracterului în t1 temporar
    lb      a1, 0(t1)           # Pune caracterul în a1 (argumentul de afișare)
    li      a0, 11              # Pune codul 11 (print_char) în a0
    ecall                       

    # 5. Epilog: Restaurare stivă și revenire
    lw      ra, 12(sp)          
    addi    sp, sp, 16          
    ret