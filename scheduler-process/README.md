# SchedulerProcess
Sistemas Operacionais - Anhembi Morumbi 

Esta atividade consiste na implementação de um programa que simule um escalonador Round-Robin preemptivo conforme os estudos realizados durante as aulas de Sistemas Operacionais

O escalonador deve contemplar o funcionamento usual do algoritmo e também deve possuir a funcionalidade de haver preempção devido a operação de I/O que cada processo possa solicitar. Desta forma o escalonador deve levar em consideração o quantum de tempo que um processo pode utilizar a CPU, assim como quando o processo necessitar uma operação de I/O deve ser retirado de execução na CPU. Assim todo processo que for retirado da CPU pelo escalonador, seja porque o quantum expirou ou por necessidade de uma operação de I/O, ele deve ser colocado no final da Fila de Pronto (fila de espera). Caso ocorra de um novo processo chegar no mesmo instante em que um processo que estava em execução e foi retirado da CPU para a fila de espera, o processo em execução terá prioridade em relação ao novo processo. 
 
O simulador deve ter como entrada as informações de cada processo como PID, duração, tempo de chegada, caso tenha operação de I/O quando elas devem ser executadas (em relação ao seu tempo de execução). O tempo do quantum também deve ser descrito no início da simulação. 

Após a leitura de dados, o simulador deve apresentar o resultado de execução dos processos, como um diagrama de Gantt, calculando o tempo de espera de cada processo e o tempo de espera médio. 
