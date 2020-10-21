# LifeGameAnhembiMorumbi
Projeto de Técnicas de  Programação 3° semestre Ciências da computação

### Do Tabuleiro e das Regras
O jogo da vida é um jogo que pode ser simulado numa tela de computador. Ele é jogado  em  tabuleiro,  idealmente  infinito.  Cada  uma  das  células  do  tabuleiro  pode estar em um de dois estados: viva ou morta. O estado do jogo é definido pelo estado das suas células.

Neste universo bi-dimensional e discreto, o tempo flui em passos discretos. A situação do  universo  em  um  dado  instante  é  chamada  de  geração.  Uma  geração  define como  será  a  próxima  e  assim  por  diante,  de  acordo  com  algumas  regras,  numa evolução determinística.

As regras do jogo da vida que determinam a evolução de uma geração para a sua sucessora são simples e locais, isto é, a situação seguinte de uma célula só depende dela e de suas oito células vizinhas. 

A  evolução  das  figuras  no  universo  do  jogo  da  vida  lembra  o  crescimento  de  uma colônia  de  bactérias.  Isto  pode  ser  utilizado  como  uma  analogia  para  facilitar  a memorização das regras do jogo:

* Se a célula está viva e tem menos de dois vizinhos, ela morre de solidão. Se ela   tem   mais   de   três   vizinhos,   ela   morre   por   problemas   devidos   à superpopulação.

* Uma  célula  morta  rodeada  por  três  células  vivas  resultará  em  uma  célula viva na próxima geração.

* Uma célula viva, adjacente a duas ou três células vivas, permanece viva.

Um  aspecto  importante  a  considerar  é  que  todas  as  células  vão  de  geração  a geração ao mesmo tempo. 

### O papel do jogador 
Enquanto nos outros jogos o “jogador” tem participação importante no desenrolar do jogo,  isto  não  acontece  no  jogo  da  vida.  Aqui  o  jogador  simplesmente  define  uma configuração inicial e daí, mecanicamente, o jogo se desenvolve sozinho. 

Pensando nisso,cada grupo deverá criar um algoritmo em C, utilizando o CodeBlocks, para  simular  a  evolução  de  uma  população  para  uma  certa  quantidade  de gerações. 

### Entrada
A entrada será através de um arquivo, composto pelos seguintes itens:

* Quantidade de linhas da matriz MxM, onde M é a sua quantidade de linhas (matriz quadrada). O tamanho máximo de M será 1000.

* M linhas com M caracteres em cada linha, onde cada caractere pode ser 0 ou 1 (a própria matriz). 

A tabela a seguir ilustra um exemplo de um arquivo de entrada válido. A primeira linha mostra a  quantidade  de  linhas,  e  as  demais  linhas  são  os  valores  da  matriz  de  entrada (população  inicial).  Conforme  o  exemplo  da  tabela a seguir,  a  matriz  possui  10  linhas (informação presente na primeira linha do arquivo).


10        |
----------|
1111000000|
1100010010|
0110111100|
1011001000|
0000001001|
1110111110|
0101011101|
1001110000|
1010111010|
0111101001|

### Funcionamento
Seu algoritmo deve calcular as gerações seguintes, até atingir a geração solicitada através  da  linha de  comando.  A  seguir temos um exemplo do  disparo  da  execução  através  da linha de comando, onde após o nome do executável, tem-se o arquivo de entrada e a quantidade de gerações para a população.
```
D:\lifegame.exe teste10.txt 500
```

### Saída
A  saída  do  algoritmo  é  um  arquivo  contendo  a  configuração  da  população  após todas as gerações terem ocorrido. A tabela a seguir mostra um exemplo de arquivo de saída (com M igual a 10), sendo que ele contém M linhas e M colunas. Cada célula contém 0 ou 1, de acordo com o valor obtido na última geração. 

10        |
----------|
1111000000|
1100010010|
0110111100|
1011001000|
0000001001|
1110111110|
0101011101|
1001110000|
1010111010|
0111101001|
