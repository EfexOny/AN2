module reg_rez (
    input wire clk,
    input wire res,
    input wire [7:0] d,
    input wire pl_rez,
    output reg [7:0] q
);
    // Incarca cuvaloarea finala a acumulatorului cand e gata
    always @(posedge clk or posedge res) begin
        if (res)
            q <= 8'h00;
        else if (pl_rez)
            q <= d;
    end
endmodule
