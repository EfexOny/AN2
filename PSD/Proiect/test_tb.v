module test_tb;

    reg [7:0] a;
    reg clk,load;
    wire [7:0] b;

    register uut(
        .load(load),
        .clk(clk),
        .a(a),
        .b(b)
);

    always #5 clk = ~clk;

    initial begin
        $display("clk  load a b");
        $monitor("%b     %b    %h     %h", clk,load, a, b);


        clk=0;
        load=0;
        a = 8'h00; #10; 


        a = 8'hAA; #10;

        load=1;
        #10;

        a = 8'hBB;


    end
    $finish
endmodule