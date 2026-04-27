module reg4 (
    input wire clk,
    input wire res,
    input wire [3:0] d,
    input wire pl1,
    output reg [3:0] q
);
    // Acest registru retine valoarea primului operand (A)
    always @(posedge clk or posedge res) begin
        if (res)
            q <= 4'b0000;
        else if (pl1)
            q <= d;
    end
endmodule
