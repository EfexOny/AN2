`timescale 1ns / 1ps

module fsm_tb();
    reg [1:0] in;
    reg clk;
    reg reset;
    wire [2:0] out;

    fsm uut (
        .in(in),
        .clk(clk),
        .reset(reset),
        .out(out)
    );

    always #5 clk = ~clk;

    initial begin
        $dumpfile("fsm_test.vcd");
        $dumpvars(0, fsm_tb);

        clk = 0;
        reset = 1;
        in = 2'b00;

        #15 reset = 0;
        #10 in = 2'b10; 
        #10 in = 2'b01; 
        #10 in = 2'b10; 
        #10 in = 2'b11;
        #10 in = 2'b00;
        
        #20 $finish;
    end
endmodule