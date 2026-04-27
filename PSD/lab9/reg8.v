module reg8 (
    input wire clk,
    input wire res,
    input wire [7:0] d,
    input wire pl3,
    output reg [7:0] q
);
    // Acumulatorul de 8 biti (tine minte suma partiala la fiecare pas al buclei)
    always @(posedge clk or posedge res) begin
        if (res)
            q <= 8'h00;
        else if (pl3)
            q <= d;
    end
endmodule
