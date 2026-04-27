module adder (
    input wire [3:0] a,
    input wire [7:0] b,
    output wire [7:0] sum
);
    // Block combinational logic pur ce aduna A la B-ul din acumulator.
    // Concateneaza 4 de '0' pentru a alinia operandul de 4 biti (a) la dimensiunea de 8 biti.
    assign sum = b + {4'b0000, a};

endmodule
