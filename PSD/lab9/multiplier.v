module MULTIPLIER (
    input wire clk,
    input wire res,
    input wire [3:0] di,
    input wire start,
    output wire [7:0] do_out,
    output wire done
);

    // --------------------------------------------------------------------------
    // FIRE (WIRES) TOP-LEVEL. 
    // FSM-ul si DATAPATH-ul nu "stiu" unul de altul. Avem nevoie de un
    // canal prin care FSM-ul trimita cabluri de control (pl1, pl2, dec) 
    // circuitului si un cablu de feedback prin care sa primeasca (cnt_zero).
    // --------------------------------------------------------------------------
    
    // Semnale de control de la FSM catre Datapath
    wire pl1_w, pl2_w, pl3_w, dec_w, pl_rez_w;
    
    // Semnal de flag (conditie) the la Datapath catre FSM
    wire cnt_zero_w;

    // 1. Instantiere Controller (FSM)
    controller U_AUT (
        .clk(clk),
        .res(res),
        .start(start),         // Buton din exterior
        .cnt_zero(cnt_zero_w), // Primeste de la Datapath pe firul `cnt_zero_w` starea = 0
        
        .pl1(pl1_w),           // Iese semnal si il legam de firul intern `pl1_w`
        .pl2(pl2_w),
        .pl3(pl3_w),
        .dec(dec_w),
        .pl_rez(pl_rez_w),
        .done(done)            // Iese direct in pin-ul Modului Principal MULTIPLIER
    );

    // 2. Instantiere Datapath (Circuit Date)
    datapath U_CIRC (
        .clk(clk),
        .res(res),
        .di(di),               // Primeste Date din afara Modulului Principal
        
        .pl1(pl1_w),           // Modulul Primeste curent din firul intern `pl1_w` dat de FSM!
        .pl2(pl2_w),
        .pl3(pl3_w),
        .dec(dec_w),
        .pl_rez(pl_rez_w),
        
        .cnt_zero(cnt_zero_w), // Produce semnal in functie de calcule, il scrie pe firul `cnt_zero_w` ca FSM-ul sa auda
        .do_out(do_out)        // Rezultatul final pleaca direct in pinul extern do_out.
    );

endmodule
