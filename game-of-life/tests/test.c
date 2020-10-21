#include "cell_test.c"

int main() {
	printf("*********************** BEGIN : TEST UNIT ****************************\n");

	printf("RUN : TestReadFile \n");
	TestReadFile();
	printf("END : TestReadFile \n");

	printf("\n\n");

	
	printf("RUN : TestGetNeighbors \n");
	TestGetNeighbors();
	printf("END : TestGetNeighbors \n");

	printf("\n\n");

	printf("RUN : TestIsAlive \n");
	TestIsAlive();
	printf("END : TestIsAlive \n");

	printf("\n\n");

	printf("RUN : TestIsStayAlive \n");
	TestIsStayAlive();
	printf("END : TestIsStayAlive \n");

	printf("*********************** END :TEST UNIT ****************************\n");

	

	printf("\n\n");
	return 1;
}
