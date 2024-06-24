**EuroThreadSoccer**
Sviluppare un simulatore di match per gli Europei 2024. La partita viene gestita da un arbitro (variable condivisa da entrambe le squadre) tramite una variabile privata “turno” che identifica la squadra in possesso di palla, inizializzata ad “A” (ipotizzando che A identifica la prima squadra e B la seconda).
Ogni squadra quindi sarà un thread avente le variabili:
- Id squadra
- Goal fatti (inizializzata a 0)
Il software effettua un ciclo di 90 iterazioni di un secondo (usando sleep) in cui ad ogni ciclo ogni squadra si comporta in base al seguente schema:
- La squadra è in possesso di palla (sulla base del turno) lancia un dado con 3 possibilità. 
1. La squadra segna un goal (incrementando il numero di goal fatti nella variabile privata della squadra). Cede la palla all’avversario e va in wait.
2. La squadra sbaglia e perde palla. Cede la palla all’avversario e va in wait.
3. Mantiene palla
- La squadra non è in possesso palla e non è in attesa, sveglia l’altra squadra e si mette in attesa.

Alla fine della partita stampare il risultato finale.

Bonus: se la squadra mantiene il possesso palla più di 3 volte perde cmq il possesso palla 
