#include "cell.c"
#include <time.h>
#include <stdlib.h>
#include <stdio.h> 

int main(int argc, char *argv[]) {
    int linha, coluna, generation;
    int cont_kill, cont_rise;    
    cell *vKill;
    cell *vRise;
    cell *population;
    int MAX_GENERATION = atoi(argv[2]);

    int population_size = 5;
    int kill_rise_size = 100;

    vKill = (cell*)calloc(kill_rise_size, sizeof(cell));
    vRise = (cell*)calloc(kill_rise_size, sizeof(cell));

    printf("******************* POPULACAO *****************\n");
    population = importPopulation(&population_size, argv[1], LIMIT_CELLS );

    setLIMIT(population_size);
    for (linha = 0; linha < population_size; linha++){
        for (coluna = 0; coluna < population_size; coluna++){
            printf("%d  ", population[linha][coluna]);
        }
        printf("\n");
    }
    printf("******************* POPULACAO *****************\n");
    printf("\n");

    for (generation = 0; generation < MAX_GENERATION; generation++) {
	cont_kill = cont_rise = 0;
	for (linha = 0; linha < population_size; linha++) {
	    for (coluna = 0; coluna < population_size; coluna++) {
		if (isStayAlive(population, coluna, linha)) {
		    vRise[cont_rise] = &population[linha][coluna];
		    cont_rise++;
		} else {
		    vKill[cont_kill] = &population[linha][coluna];
		    cont_kill++;
		}
	    }
	}

	kill(vKill, cont_kill);
	rise(vRise, cont_rise);
    }


    printf("\n\n\n");
    printf("************** RESULTADO *****************\n");
    for (linha = 0; linha < population_size; linha++){
        for (coluna = 0; coluna < population_size; coluna++){
            printf("%d  ", population[linha][coluna]);
        }
        printf("\n");
    }
    printf("************** RESULTADO *****************");
    free(vKill);
    free(vRise);
    free(population);
    return 1;
}
