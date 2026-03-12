.data
mesaj: .string "Salut din RISC-V!\n"

.text
main:
    li a0, 4        
    la a1, mesaj 
    ecall           
