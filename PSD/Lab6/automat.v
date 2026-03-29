module fsm(
    input [1:0] in,
    input clk, reset,
    output reg [1:0] out
);

    reg [1:0] cs, ns;

    always @(posedge clk or posedge reset) begin
        if (reset)
            cs <= 2'b00;
        else
            cs <= ns;
    end

    always @(posedge clk) begin
        ns = cs;
        case(cs)
            2'b00: begin
                if (in == 2'b11)      ns = 2'b01;
                else if (in == 2'b00) ns = 2'b10;
            end
            2'b01: begin
                if (in == 2'b10)      ns = 2'b01;
                else if (in == 2'b01) ns = 2'b10;
            end
            2'b10: begin
                if (in == 2'b10)      ns = 2'b01;
                else if (in == 2'b01) ns = 2'b10;
                else if (in == 2'b11) ns = 2'b11;
            end
            2'b11: begin
                if (in == 2'b11)      ns = 2'b11;
                else                  ns = 2'b00;
            end
            default: ns = 2'b00;
        endcase
    end

    always @(posedge clk) begin
        case(cs)
            2'b00: out = 2'b01;
            2'b01: out = 2'b10;
            2'b10: out = 2'b11;
            2'b11: out = 2'b00;
            default: out = 2'b00;
        endcase
    end

endmodule