`timescale 1ns / 1ps

module tb_multiplier;

    reg clk;
    reg res;
    reg [3:0] di;
    reg start;
    
    wire [7:0] do_out;
    wire done;

    // Instantierea modulului principal
    MULTIPLIER uut (
        .clk(clk),
        .res(res),
        .di(di),
        .start(start),
        .do_out(do_out),
        .done(done)
    );

    // Generarea semnalului de ceas
    initial begin
        clk = 0;
        forever #5 clk = ~clk; // Perioada de 10ns
    end

    // Testul propriu-zis
    initial begin
        // 1. Initializare si Reset
        $display(">> Incepere simulare...");
        res = 1;
        start = 0;
        di = 4'd0;
        #20;
        res = 0; // Eliberare reset (activ pe 1)
        #20;
        
        // -------------------------------------------------------------
        // Test 1: Inmultim 3 * 4 = 12
        // -------------------------------------------------------------
        $display(">> Test 1: Calculam 3 * 4");
        
        // S0: Setam Operandul A (3) pe intrarea DI.
        // Automatul asteapta start=1. Reg4 este incarcat cu 3.
        di = 4'd3; 
        #20;
        
        // Apasam butonul de Start
        start = 1; 
        #10; // Tranzitie S0 -> S1
        
        // S1: Setam Operandul B (4) pe intrarea DI.
        // Automatul asteapta start=0. Cnt4 este incarcat cu 4.
        di = 4'd4;
        #20;
        
        // Eliberam butonul de Start
        start = 0; // Tranzitie S1 -> S2 (incepe calculul)
        
        // Asteptam ca automatul sa termine si sa ajunga in starea S5 (done=1)
        wait(done == 1);
        #10;
        
        if (do_out == 8'd12)
            $display(">> Test 1 PASSED! Rezultat = %d", do_out);
        else
            $display(">> Test 1 FAILED! Rezultat asteptat: 12, obtinut: %d", do_out);
            
        #20;

        // Resetam sistemul pentru al doilea test
        res = 1;
        #20;
        res = 0;
        #20;
        
        // -------------------------------------------------------------
        // Test 2: Inmultim 5 * 2 = 10
        // -------------------------------------------------------------
        $display(">> Test 2: Calculam 5 * 2");
        
        di = 4'd5;
        #20;
        
        start = 1;
        #10;
        
        di = 4'd2;
        #20;
        
        start = 0;
        
        wait(done == 1);
        #10;
        
        if (do_out == 8'd10)
            $display(">> Test 2 PASSED! Rezultat = %d", do_out);
        else
            $display(">> Test 2 FAILED! Rezultat asteptat: 10, obtinut: %d", do_out);
        
        #20;

        // Resetam sistemul pentru al treilea test
        res = 1;
        #20;
        res = 0;
        #20;

        // -------------------------------------------------------------
        // Test 3: Inmultim 6 * 0 = 0 (Corner case evaluat)
        // -------------------------------------------------------------
        $display(">> Test 3: Calculam 6 * 0 (Corner case)");
        
        di = 4'd6;
        #20;
        
        start = 1;
        #10;
        
        di = 4'd0; // Inmultim cu 0 -> Rezultatul trebuie sa fie tot 0? 
                   // Conform modului grafic in care a fost scris automatul pe tabla,
                   // desi scrie ca aduna intotdeauna in S2 prima data. Sa vedem ce iese!
        #20;
        
        start = 0;
        
        wait(done == 1);
        #10;
        
        $display(">> Test 3 (6*0) Finalizat. Rezultat obtinut: %d", do_out);
        
        #50;
        $display(">> Simulare incheiata.");
        $finish;
    end

endmodule
