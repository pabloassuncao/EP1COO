# Onitama
Onitama é um jogo de tabuleiro de 2 jogadores. Cada jogador reveza a vez para jogar cartas de movimento que irão mover suas peças. O objetivo é derrotar o adversário em uma disputa de artes marciais. Neste divertido jogo, jogadores precisam pensar em estratégias para qual movimento realizar na hora certa, defendendo seu Templo e seu Mestre. Saber quando ser ofensivo ou defensivo é a chave!

Uma partida do jogo Onitama ocorre em um tabuleiro de dimensão 5 × 5, e deve respeitar as seguintes regras/condições:
- O espaço central das linhas superior e inferior do tabuleiro é denominado Templo. Cada Templo está associado a um jogador, e iremos assumir que o Templo da linha superior terá a cor azul, enquanto o da linha inferior terá a cor vermelha.
- Cada jogador possui 5 peças: um Mestre e 4 alunos;
- Na configuração inicial do tabuleiro, todas as peças do jogador azul ocupam a linha superior do tabuleiro, enquanto as peças do jogador vermelho a linha inferior, com os mestres ocupando seus respectivos Templos;
- O Mestre funciona da mesma forma que as peças de aluno, a única diferença é que serve para condições de vitória;
- Cada jogador também possui duas cartas de movimento na mão;
- Uma carta de movimento é escolhida para ser a carta da mesa. Essa carta possui uma cor, que determinará o jogador que começará a partida;
- Ou seja, em cada partida, terá apenas 5 cartas em jogo: duas na mão de cada jogador e uma na mesa.
- Cartas de movimento indicam para quais posições uma peça pode se mover;
- Uma jogada consiste em “aplicar” uma carta a uma peça e movê-la para uma posição disponível de acordo com as possibilidades definidas na carta. Ao realizar um movimento, uma peça não pode se mover para fora do tabuleiro e nem para um espaço contendo uma peça da mesma cor;
- Caso uma peça se mova para um espaço contendo uma peça da cor do oponente, a peça que estava no espaço é eliminada;
- Após utilizar uma carta, o jogador deve trocar a carta recém-utilizada pela carta da mesa. Assim, a carta que antes era da mesa vai para a mão do jogador; e a carta recém-utilizada vai para a mesa;
- Após uma jogada, na qual é feito um movimento, o jogador passa a vez para seu oponente;
- O jogo termina quando uma destas condições de vitória é atendida:
	- Um dos Mestres é capturado;
	- Um dos Mestres ocupa o Templo adversário.

O livro completo com as regras do jogo é disponibilizado publicamente pelo seu editor (em inglês) em https://www.arcanewonders.com/wp-content/uploads/2021/05/Onitama-Rulebook.pdf.

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:
<!---Estes são apenas requisitos de exemplo. Adicionar, duplicar ou remover conforme necessário--->
* Você instalou a versão mais recente de `JAVA` e configurou seu ambiente corretamente
* Você tem uma máquina `<Windows / Linux / Mac>`.

## 🚀 Instalando Onitama

Para instalar o Onitama, execute na pasta raiz

Linux e macOS:
```
find . -name "*.java" ! -name "*Test*" -exec javac {} +
```
Windows (Powershell):
```
Get-ChildItem -Recurse -Filter *.java | Where-Object { $_.Name -notlike "*Test*" } | ForEach-Object { javac $_.FullName }
```
Windows (Prompt):
```
for /r %G in (*.java) do (
    echo %~nG | find /i "Test" >nul || javac "%G"
)
```

## ☕ Jogando Onitama

Para jogar, basta rodar os seguintes passos:

- Determine quem vai ser o Player Vermelho e quem vai ser o Player Azul
- Execute o seguinte comando na pasta raiz
	-	```java Main```
-	Jogue seguindo o que for pedido no terminal

### Obs: Você pode estudar o código e criar novas cartas e mecânicas pro jogo.

## ☕ Testando Onitama

Para rodar os testes execute:

Linux e macOS:
```
javac -cp .:./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar *Test.java && java -cp .:./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore SpotTest PositionTest GameImplTest PlayerTest CardTest PieceTest;
```

### Obs: Você pode estudar o código e criar novas cartas e mecânicas pro jogo.
[⬆ Voltar ao topo](#nome-do-projeto)