module cnt4 (
    input wire clk,
    input wire res,
    input wire [3:0] d,
    input wire pl2,
    input wire dec,
    output reg [3:0] q,
    output wire cnt_zero
);
    // Acest contor incarca valoarea lui B si scade la fiecare semnal DEC
    always @(posedge clk or posedge res) begin
        if (res)
            q <= 4'b0000;
        else if (pl2)
            q <= d;
        else if (dec)
            q <= q - 1'b1;
    end
    
    // Semnalizeaza automatului de control ca inmultirea trebuie terminata
    assign cnt_zero = (q == 4'b0000);

endmodule
