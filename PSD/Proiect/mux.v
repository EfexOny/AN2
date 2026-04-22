module mux(
    input [7:0] a,b,
    input sel,
    output [7:0] c
);

assign c = (sel == 0) ? a : b;


endmodule