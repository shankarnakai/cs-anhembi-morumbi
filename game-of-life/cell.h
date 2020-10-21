#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utils.h"

//ERRORS
#define CELL_ERR_INDEX_OUT_OF_RANGE "Index Out Of Range Exception when try acess some cell"
#define CELL_ERR_FILE_NOT_FOUND "File not found in path passed"
#define CELL_ERR_FILE_NOT_OPEN "Unexpected error to open file"


//Cell functions
typedef enum e_direction {
	NORTH  =  1,
	SOUTH  =  2,
	EAST   =  3,
	WEST   =  4
} direction;

typedef enum e_state {
	ALIVE = 1,
	DEAD = 0
} state;

/** 
 * Alias to pointer of int, used to turn de code more verbose
 */
typedef int *cell;


//FUNCTIONS

void setLIMIT(int limit);
/** 
 * Verify if cell can stay alive in this generation  
 * 
 * @param cells its a typedef of pointer int 
 * @param x  its index horizontal of array you can check
 * @param y  its index vertical of array you can check
 * @return  true to if cell can stay alive, and false if can't stay
  */
bool isStayAlive(cell *c, int x, int y);

/** 
 * Verify if cell is current alive  
 *
 * @param cells its a typedef of pointer int 
 * @param x  its index horizontal of array you can check
 * @param y  its index vertical of array you can check
 * @return  true to if current alive, and false if can't 
  */
bool isAlive(cell *c, int x, int y);

/** 
 * Verify if cell can turn to alive in next generation  
 *
 * @param cells its a typedef of pointer int 
 * @param x  its index horizontal of array you can check
 * @param y  its index vertical of array you can check
 * @return  true to if cell can turn to be alive and false if can't 
  */
bool canRise(cell *c,int x, int y);


/** 
 * Verify if cell can turn to alive in next generation  
 *
 * @param cells its a typedef of pointer int 
 * @param x  its index horizontal of array you can check
 * @param y  its index vertical of array you can check
 * @return amout of neighbors to this cell
  */
int getNeighbors(cell *c, int x, int y); 

/** 
 * Kill all cell in this array 
 *
 * @param cells its a typedef of pointer int 
  */
void kill(cell *c, int size);

/** 
 * Turn to alive all cell in this array 
 *
 * @param cells its a typedef of pointer int 
  */

void rise(cell *c, int size);

/** 
 * Check if , is a valid indice 
 *
 * @param x  its index horizontal of array you can check
 * @param y  its index vertical of array you can check
  */
void __validate_index_in_range_matriz(int  x,int y); 

/** 
 * Initialize a matrix of cell with limit x and y especifed
 *
 * @param SIZE_X  its a limit on horizontal
 * @param SIZE_Y  its a limit on vertical
  */
void initialize_cells(cell *cells, int SIZE_X, int SIZE_Y);

/** 
 * Import a file to initialize the population 
 *
 * @param cells its a typedef of pointer int 
 * @param file pass a full physical path  
 * @param lenght is the amount of characters that a single line reaches 
 * @return the start generation to the game 
  */
cell * importPopulation(int *size, char *path, int lenght);
