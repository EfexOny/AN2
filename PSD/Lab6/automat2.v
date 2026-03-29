module fsm(
    input [1:0] in,
    input clk, reset,
    output reg [2:0] out
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
        out = 3'b000;
        

        case(cs)
            2'b00: begin
                if (in[1] == 1'b0) begin // 0X
                    ns = 2'b00;
                    out = 3'b000;
                end else if (in == 2'b10) begin
                    ns = 2'b01;
                    out = 3'b001;
                end
            end

            2'b01: begin
                if (in == 2'b11 || in == 2'b00) begin
                    ns = 2'b00;
                    out = 3'b000;
                end else if (in == 2'b01) begin
                    ns = 2'b10;
                    out = 3'b010;
                end else if (in == 2'b10) begin
                    ns = 2'b01;
                    out = 3'b001;
                end
            end
            
            2'b10: begin
               if (in == 2'b00) begin
                    ns = 2'b01;
                    out = 3'b001;
                end else if (in == 2'b11 || in == 2'b01) begin
                    ns = 2'b10;
                    out = 3'b010;
                end else if (in == 2'b10) begin
                    ns = 2'b11;
                    out = 3'b100;
                end
            end

            2'b11: begin
              if (in == 2'b11 || in == 2'b01) begin
                    ns = 2'b00;
                    out = 3'b111;
                end else if (in == 2'b00) begin
                    ns = 2'b10;
                    out = 3'b010;
                end else if (in == 2'b10) begin
                    ns = 2'b11;
                    out = 3'b100;
                end
            end
        endcase
    end

endmodule