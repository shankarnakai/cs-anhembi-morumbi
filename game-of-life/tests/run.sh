gcc -o test test.c testunit.c ../cell.c 

#run file to run test
if [ -e ./test ]
then
	./test
fi
