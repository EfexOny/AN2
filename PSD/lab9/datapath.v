module datapath (
    input wire clk,
    input wire res,
    input wire [3:0] di,
    input wire pl1,
    input wire pl2,
    input wire pl3,
    input wire dec,
    input wire pl_rez,
    output wire [7:0] do_out,
    output wire cnt_zero
);
    // -------------------------------------------------------------
    // Fire (wires) interne pentru a lega blocurile.
    // Acestea leaga IESIRILE unui modul de INTARIRILE altui modul.
    // -------------------------------------------------------------
    wire [3:0] out_reg4;     // Legatura dintre REG4 si ADDER
    wire [7:0] out_reg8;     // Legatura dintre REG8 si ADDER / REG_REZ
    wire [7:0] out_adder;    // Legatura dintre Iesirea din ADDER spre REG8
    wire [3:0] out_cnt4;     // Iesirea CNT4 (ignorata aici, ne bazam pe cnt_zero)

    // 1. Instantiere REG4 (memoreaza Operandul A)
    reg4 U_REG4 (
        .clk(clk),
        .res(res),
        .d(di),            // primeste date din afara
        .pl1(pl1),         // comanda FSM
        .q(out_reg4)       // trimite valoarea memorata pe firul intern `out_reg4`
    );

    // 2. Instantiere CNT4 (memoreaza Operandul B si scade)
    cnt4 U_CNT4 (
        .clk(clk),
        .res(res),
        .d(di),
        .pl2(pl2),         // comanda incarcare din FSM
        .dec(dec),         // comanda scadere din FSM
        .q(out_cnt4),
        .cnt_zero(cnt_zero) // trimite starea conditiei spre FSM (prin Top)
    );

    // 3. Instantiere ADDER (Sumatorul)
    adder U_ADDER (
        .a(out_reg4),      // Ia operandul de sus (A) de pe firul `out_reg4`
        .b(out_reg8),      // Ia valoarea partiala adunata anterior de pe `out_reg8`
        .sum(out_adder)    // Pune rezultatul adunarii combinate in `out_adder`
    );

    // 4. Instantiere REG8 (Acumulatorul)
    reg8 U_REG8 (
        .clk(clk),
        .res(res),
        .d(out_adder),     // Preia suma imediat de la iesirea Adder-ului
        .pl3(pl3),         // Când FSM zice pl3=1, o "capteaza" definitiv
        .q(out_reg8)       // O lasa vizibila (o da inapoi la Sumator si catre ultimul registru)
    );

    // 5. Instantiere REG_REZ (Registrul de Iesire finala)
    reg_rez U_REG_REZ (
        .clk(clk),
        .res(res),
        .d(out_reg8),      // Ia numarul final de pe acumulator (out_reg8)
        .pl_rez(pl_rez),   // La FSM pl_rez=1 o afiseaza in mod oficial
        .q(do_out)         // Trimite valoarea asta spre pinul central din exteriorul sistemului
    );

endmodule
