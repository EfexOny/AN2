`timescale 1ns / 1ps

module tb_mux;
    reg a_t;
    reg b_t;
    reg sel_t;
    wire y_t;

    multiplexer_2_1 uut (
        .a(a_t),
        .b(b_t),
        .select(sel_t),
        .y(y_t)
    );

    initial begin
        $dumpfile("test_mux.vcd"); // Numele fișierului de ieșire
        $dumpvars(0, tb_mux);     // Spunem să salveze toate variabilele


        sel_t = 0; a_t = 0; b_t = 1; #10;
        sel_t = 0; a_t = 1; b_t = 0; #10;

        sel_t = 1; a_t = 0; b_t = 1; #10;
        sel_t = 1; a_t = 1; b_t = 0; #10;

        $finish; 
    end
endmodule