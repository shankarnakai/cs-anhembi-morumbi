#include "testunit.h"
#include "../cell.h"

int _LIMIT_CELLS = 5;

typedef struct t_coordinate {
	int x;
	int y;
}Coordinate;

typedef struct t_cases {
	char Description[100];
	cell *cells;
	Coordinate Position;
	bool Expected;
}Cases;

typedef struct t_cases_neighbors {
	char Description[100];
	cell *cells;
	Coordinate Position;
	int Amount;
}CasesNeighbors;

void setMatriz(cell *c, int matriz[5][5]); 
void TestReadFile() {
	setLIMIT(5);
	cell *tcells, *expected;
	int size = 5;

	expected = (cell *)malloc(5*sizeof(cell));
	initialize_cells(tcells, size, size);
	initialize_cells(expected, size, size);

	int l[5][5] = {
		{1,1,0,1,1},
		{1,0,0,1,0},
		{1,0,0,1,0},
		{1,0,0,1,0},
		{1,0,0,1,0},
	};

	setMatriz(expected, l);

	int generation =0;
	tcells = importPopulation(&generation, "./samples/lifegame.txt", 5);

	printf("   Verify generation returned \n");

	if(generation != 5) {
		FAIL("	Unexpected generation %d, expected %d\n", generation, 10);
	} else {
		SUCESS("  PASS\n");
	}

	printf("  Verify value in population\n");
	int linha = 0;
	int coluna = 0;

	for (linha = 0; linha < size; linha++) {
		for (coluna = 0; coluna < size; coluna++) {
			int cell_returned = tcells[linha][coluna];
			int cell_expected = expected[linha][coluna];
			if(cell_returned != cell_expected) {
				FAIL("	(line %d, column %d) : Unexpected value %d, expected %d\n",linha, coluna, cell_returned , cell_expected);
			} else {
				SUCESS("  PASS\n");
			}
		}
	}
	 free(tcells);
	 free(expected);
}

void TestIsStayAlive() {
	cell *tcells; 
	tcells = (cell *)malloc(5*sizeof(cell));
	initialize_cells(tcells, 5, 5);

	//that can be alive test (0,1)
	tcells[0][1] = ALIVE;
	tcells[1][1] = ALIVE;
	tcells[0][2] = ALIVE;

	//that can't be alive
	tcells[2][3] = ALIVE;
	tcells[4][4] = ALIVE;
	tcells[3][1] = ALIVE;

	Cases cases[3] = {
		{
		  .Description = "When try verify a cell that can be stay alive",	
		  .cells = tcells,
		  .Position = {.y= 0, .x=1},
		  .Expected = true
		},
		{
		  .Description = "When try verify a cell that can't be stay alive",	
		  .cells = tcells,
		  .Position = {.y= 3, .x=1},
		  .Expected = false 
		},
		{
		  .Description = "When try verify a cell dead can stay alive",	
		  .cells = tcells,
		  .Position = {.y= 0, .x=0},
		  .Expected = false 
		},
	}; 

	int i;
	for(i =0; i < 3; i += 1) {
		Cases cs = cases[i];
		printf("%s\n",cs.Description);
		bool result = isStayAlive(cs.cells, cs.Position.x, cs.Position.y);
		if(result != cs.Expected) {
		   FAIL("	Unexpected result %d, expected %d\n", result, cs.Expected);
		} else {
		   SUCESS("	PASS\n");
		}
	}
	free(tcells);
}

void TestGetNeighbors() {
	cell *tcells; 
	setLIMIT(10);

	tcells = (cell *)malloc(10*sizeof(cell));
	initialize_cells(tcells, 10, 10);

	//that can be alive test (0,1)
	tcells[0][1] = ALIVE; //has 2 neighbors
	tcells[1][1] = ALIVE;
	tcells[0][2] = ALIVE;

	//that can't be alive
	tcells[2][3] = ALIVE;
	tcells[4][4] = ALIVE;
	tcells[3][1] = ALIVE;

	CasesNeighbors cases[3] = {
		{
		  .Description = "When have a two neighbors",	
		  .cells = tcells,
		  .Position = {.x= 1, .y=0},
		  .Amount = 2 
		},
		{
		  .Description = "When have a no one neighbors",	
		  .cells = tcells,
		  .Position = {.x= 1, .y=3},
		  .Amount = 0 
		},
	}; 

	int i;
	for(i =0; i < 2; i += 1) {
		CasesNeighbors cs = cases[i];
		printf("%s\n",cs.Description);
		int result = getNeighbors(cs.cells, cs.Position.x, cs.Position.y);
		if(result != cs.Amount) {
		   FAIL("	Unexpected amount of neighbors %d, expected %d\n", result, cs.Amount);
		} else {
		   SUCESS("	PASS\n");
		}
	}
	free(tcells);
}

void TestIsAlive() {
	cell *tcells; 
	tcells = (cell *)malloc(5*sizeof(cell));
	initialize_cells(tcells, 5,5);

	//alive test
	tcells[0][1] = ALIVE;
	tcells[2][3] = ALIVE;
	tcells[4][4] = ALIVE;
	tcells[3][1] = ALIVE;

	Cases cases[3] = {
		{
		  .Description = "When try verify a cell alive",	
		  .cells = tcells,
		  .Position = {.y= 0, .x=1},
		  .Expected = true
		},
		{
		  .Description = "When try verify a cell dead",	
		  .cells = tcells,
		  .Position = {.y= 0, .x=0},
		  .Expected = DEAD
		},
	}; 

	int i;

	for(i =0; i < 2; i += 1) {
		Cases cs = cases[i];
		printf("%s\n",cs.Description);
		bool result = isAlive(cs.cells, cs.Position.x, cs.Position.y);
		if(result != cs.Expected) {
		   FAIL("	Unexpected result %d, expected %d\n", result, cs.Expected);
		} else {
		   SUCESS("	PASS\n");
		}
	}
	free(tcells);
} 

void setMatriz(cell *c, int matriz[5][5]) {
	int linha, coluna;
	for (linha = 0; linha < 5; linha++) {
		for (coluna = 0; coluna < 5; coluna++) {
			c[linha][coluna] = matriz[linha][coluna];
		}
	}
}
