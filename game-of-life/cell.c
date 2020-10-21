#include <stdio.h>
#include "cell.h"

int LIMIT_CELLS=1000;
void setLIMIT(int limit) {
	LIMIT_CELLS = limit;
}

bool isStayAlive(cell *cells, int x, int y) {
	__validate_index_in_range_matriz(x,y);

	if(isAlive(cells, x, y) == false) {
		return false; 
	}

	int neighbors = getNeighbors(cells, x, y);
	if(neighbors < 2) {
		return false;	
	} else if(neighbors > 3) {
		return false;
	}

	return true;
}

bool isAlive(cell *c, int x, int y) {
	__validate_index_in_range_matriz(x,y);

	return (c[y][x] == ALIVE);
} 

bool canRise(cell *c,int x, int y) {
	__validate_index_in_range_matriz(x,y);
	return (c[y][x] == ALIVE);
}

int getNeighbors(cell *c, int x, int y) {
	__validate_index_in_range_matriz(x,y);
	int neighbors = 0;

	//verify left
	if(x > 0 && c[y][x-1] == ALIVE)  {
		neighbors += 1;	
	}

	//verify right 
	if(x < (LIMIT_CELLS-1) && c[y][x+1] == ALIVE)  {
		neighbors += 1;	
	}

	//verify top
	if(y > 0 && c[y-1][x] == ALIVE)  {
		neighbors += 1;	
	}

	//verify bottom
	if(y < (LIMIT_CELLS-1) && c[y+1][x] == ALIVE)  {
		neighbors += 1;	
	}

	return neighbors;
}

void kill(cell *c, int size) {
	int i;

	for (i = 0; i < size; i++) {
		*c[i] = 0;
	}
}

void rise(cell *c, int size){
    int i;

    for (i = 0; i < size; i++) {
        *c[i] = 1;
    }
}

cell * importPopulation(int *size, char *path, int length ) {
	char buff[length+2];
	cell *c;
	FILE *file;

	file = fopen(path, "r");
	if (file == NULL) {
		fprintf(stderr, "%s: %s\n", CELL_ERR_FILE_NOT_OPEN, path);
		exit(EXIT_FAILURE);
	}

	*size = -1;

	int linha = 0;
	while (fgets(buff,sizeof buff, file) != NULL){
		 if(*size == -1) {
			*size = atoi(buff);

			c = calloc(*size , sizeof(cell)); 
			int j = 0;
			for (j = 0; j < *size; j++) {
				c[j] = calloc(*size , sizeof(cell));
			}
			continue;
		} 

		int coluna; 
		for(coluna=0; coluna < *size; coluna++) {
			if(buff[coluna] == '\n' ||
			   buff[coluna] == ' '  ||
			   buff[coluna] == '\0'  
			) { continue; }

			if(buff[coluna] == '0') {
				c[linha][coluna]= 0; 
			} else if(buff[coluna] == '1') {
				c[linha][coluna]= 1; 
			}
		}
		linha++;
	}
	fclose(file);

	return c;
}

//HELPERS
void initialize_cells(cell *cells, int SIZE_X, int SIZE_Y) {
	int x = 0;
	int y = 0;

	for(x = 0; x <  SIZE_X; x+=1) {
		cells[x] = (cell)malloc(LIMIT_CELLS*sizeof(int));
		for(y = 0; y <  SIZE_Y; y+=1) { 
			cells[x][y] = 0;	
		}
	}
}

void __validate_index_in_range_matriz(int  x,int y) {
	if(x < 0 || y < 0 || x > LIMIT_CELLS || y > LIMIT_CELLS) {
		fprintf(stderr, "%s: line = %d, column = %d", CELL_ERR_INDEX_OUT_OF_RANGE, y, x);
		exit(EXIT_FAILURE);
	}
}
