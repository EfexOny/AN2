module register(
    input load,
    input clk,
    input [7:0] a,
    output reg [7:0] b
);

always @(posedge clk) begin
    if(load == 1) begin
        b <= a;
    end
end

endmodule