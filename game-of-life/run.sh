gcc -o main main.c 

#run file to run test
if [ -e ./main ]
then
	./main ./tests/samples/lifegame.txt 1
fi
