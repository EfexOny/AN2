`timescale 1ns / 1ps

module fsm_tb;
    reg [1:0] in;
    reg clk, reset;
    wire [1:0] out;

    fsm dut (.in(in), .clk(clk), .reset(reset), .out(out));

    always #5 clk = ~clk;

    initial begin
        $dumpfile("simulation.vcd");
        $dumpvars(0, fsm_tb);

        clk = 0; 
        reset = 1; 
        in = 2'b01;
        #15 reset = 0;
        
        @(posedge clk); 
        #1 in = 2'b11; 
        
        @(posedge clk);
        #1 in = 2'b01;
        
        @(posedge clk);
        #1 in = 2'b11;
        
        @(posedge clk);
        #1 in = 2'b00;

        $finish;
    end

endmodule