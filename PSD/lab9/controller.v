module controller (
    input wire clk,
    input wire res,
    input wire start,
    input wire cnt_zero,
    output reg pl1,
    output reg pl2,
    output reg pl3,
    output reg dec,
    output reg pl_rez,
    output reg done
);

    parameter S0 = 3'd0;
    parameter S1 = 3'd1;
    parameter S2 = 3'd2;
    parameter S3 = 3'd3;
    parameter S4 = 3'd4;
    parameter S5 = 3'd5;

    reg [2:0] state, next_state;

    // Registrul de stare
    always @(posedge clk or posedge res) begin
        if (res)
            state <= S0;
        else
            state <= next_state;
    end

    // Logica de tranzitie a starilor (combinationala)
    always @(*) begin
        next_state = state; 
        case (state)
            S0: if (start == 1'b1) next_state = S1; // Asteptam apasarea START (x=1)
            S1: if (start == 1'b0) next_state = S2; // Asteptam eliberarea START (x=0)
            S2: if (cnt_zero) next_state = S4;      // cnt == 0 -> terminam (0)
                else          next_state = S3;      // cnt != 0 -> continuam 
            S3: next_state = S2;                    // Inapoi la evaluare
            S4: next_state = S5;                    // Tranzitie catre sfarsit
            S5: if (start == 1'b0) next_state = S0; // Revenire doar cand x=0
            default: next_state = S0;
        endcase
    end

    // Iesirile automatului (functia Moore)
    always @(*) begin
        pl1 = 0; pl2 = 0; pl3 = 0; dec = 0; pl_rez = 0; done = 0;
        case (state)
            S0: pl1 = 1'b1;         // Incarcam reg4 u operandul A
            S1: pl2 = 1'b1;         // Incarcam cnt4 cu operandul B
            S2: ;                   // Evaluam daca se inchide bucla
            S3: begin pl3 = 1'b1; dec = 1'b1; end // Efectuam calculul (sumator si decrement)
            S4: pl_rez = 1'b1;      // Pregatim rezultatul in OUT
            S5: done = 1'b1;        // Finalizat
            default: ;
        endcase
    end

endmodule
