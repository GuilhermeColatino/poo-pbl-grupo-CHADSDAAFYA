# Sistema de Divisão de Despesas
Tema 10: Aplicativo de Finanças Coletivas para Repúblicas ou Grupos de Viagem \
Matéria: Projeto de Programação

---
## Membros
* Guilherme Colatino
* Rogério Silva
* Lucas Eloi
* Ygor Thyago
* Renato Júlio

---
## Descrição
Software para organização de despesas entre grupos de pessoas, projetada para facilitar a divisão financeira (como viagens, repúblicas ou eventos). Os usuários podem cadastrar membros e registrar despesas selecionando dinamicamente quem participou daquele gasto, contando com diferentes estratégias matemáticas (igualitária ou percentual).

---
## Dependências

* Java 25 (ou superior)
* Maven
* JUnit

---
## Como Executar
Abra o terminal do seu sistema operacional e execute o comando abaixo para clonar o projeto:
```bash
https://github.com/GuilhermeColatino/poo-pbl-grupo-CHADSDAAFYA.git
```

---
## Domain-Driven Design (DDD)
* Value Objects: Em vez de usar um simples double (que pode sofrer com problemas de arredondamento e falta de semântica), criamos o Value Object Dinheiro. Ele encapsula o valor e garante regras (como não aceitar dinheiro negativo).
* Aggregate Root: A classe grupo funciona como a Raiz do Agregado. O Membro não deve atualizar seu saldo de forma isolada. Quem dita as regras é o Grupo.
* Entities: objetos que possuem uma identidade única que não muda, independentemente de seus atributos mudarem.
  * Grupo - Mesmo que você adicione ou remova membros, ou mude os saldos, ele continua sendo o mesmo grupo.
  * Membro - Cada Membro tem um id único. Se ele mudar de nome, o saldo dele e o histórico continuam atrelados àquele ID único.
* Strategy: Centraliza as regras matemáticas de divisão utilizando o padrão Strategy (Classe e Interface Divisao), isolando os algoritmos de cálculo do restante da aplicação.

---
## Exemplo de Execução
```bash
=== BEM-VINDO AO SISTEMA DE DIVISÃO DE DESPESAS ===
Você ainda não possui nenhum grupo cadastrado.
1 - Criar Novo Grupo
0 - Sair do Sistema
Escolha uma opção obrigatória: 1
Digite o nome do novo grupo (ex: Viagem, Republica): Viagem
Grupo 'Viagem' criado e salvo com sucesso!
Grupo 'Viagem' criado com sucesso!
=== MENU PRINCIPAL [Grupo: Viagem] ===
1 - Cadastrar Novo Membro no Grupo
2 - Adicionar Saldo a um Membro do Grupo
3 - Registrar Nova Despesa
4 - Visualizar Grupo
5 - Criar Grupo
6 - Mudar de Grupo
0 - Sair
Escolha uma opção: 1
Digite o nome do novo membro: Guilherme
Guilherme adicionado ao grupo!
=== MENU PRINCIPAL [Grupo: Viagem] ===
1 - Cadastrar Novo Membro no Grupo
2 - Adicionar Saldo a um Membro do Grupo
3 - Registrar Nova Despesa
4 - Visualizar Grupo
5 - Criar Grupo
6 - Mudar de Grupo
0 - Sair
Escolha uma opção: 1
Digite o nome do novo membro: Rogerio
Rogerio adicionado ao grupo!
=== MENU PRINCIPAL [Grupo: Viagem] ===
1 - Cadastrar Novo Membro no Grupo
2 - Adicionar Saldo a um Membro do Grupo
3 - Registrar Nova Despesa
4 - Visualizar Grupo
5 - Criar Grupo
6 - Mudar de Grupo
0 - Sair
Escolha uma opção: 4
=== SALDO ATUAL DO GRUPO ===
=================================================================
 GRUPO: VIAGEM                                               
=================================================================
ID         | NOME                      | SALDO INDIVIDUAL
-----------------------------------------------------------------
0          | Guilherme                 | 0              
1          | Rogerio                   | 0              
-----------------------------------------------------------------
SALDO TOTAL DO GRUPO:                  | R$ 0,00        
=================================================================
=== MENU PRINCIPAL [Grupo: Viagem] ===
1 - Cadastrar Novo Membro no Grupo
2 - Adicionar Saldo a um Membro do Grupo
3 - Registrar Nova Despesa
4 - Visualizar Grupo
5 - Criar Grupo
6 - Mudar de Grupo
0 - Sair
Escolha uma opção: 2
Digite o ID do membro: 
0
Digite o saldo desejado: 
10000
Guilherme Agora tem um saldo de: 10000
=== MENU PRINCIPAL [Grupo: Viagem] ===
1 - Cadastrar Novo Membro no Grupo
2 - Adicionar Saldo a um Membro do Grupo
3 - Registrar Nova Despesa
4 - Visualizar Grupo
5 - Criar Grupo
6 - Mudar de Grupo
0 - Sair
Escolha uma opção: 2
Digite o ID do membro: 
1
Digite o saldo desejado: 
15000
Rogerio Agora tem um saldo de: 15000
=== MENU PRINCIPAL [Grupo: Viagem] ===
1 - Cadastrar Novo Membro no Grupo
2 - Adicionar Saldo a um Membro do Grupo
3 - Registrar Nova Despesa
4 - Visualizar Grupo
5 - Criar Grupo
6 - Mudar de Grupo
0 - Sair
Escolha uma opção: 4
=== SALDO ATUAL DO GRUPO ===
=================================================================
 GRUPO: VIAGEM                                               
=================================================================
ID         | NOME                      | SALDO INDIVIDUAL
-----------------------------------------------------------------
0          | Guilherme                 | 10000          
1          | Rogerio                   | 15000          
-----------------------------------------------------------------
SALDO TOTAL DO GRUPO:                  | R$ 250,00      
=================================================================
=== MENU PRINCIPAL [Grupo: Viagem] ===
1 - Cadastrar Novo Membro no Grupo
2 - Adicionar Saldo a um Membro do Grupo
3 - Registrar Nova Despesa
4 - Visualizar Grupo
5 - Criar Grupo
6 - Mudar de Grupo
0 - Sair
Escolha uma opção: 3
Digite o valor total da despesa: 1200
Quem pagou a despesa?
[ 1 ] Guilherme
[ 2 ] Rogerio
Escolha o número do pagador: 1
=== QUEM VAI DIVIDIR ESSA CONTA? ===
Digite o ID do membro para adicionar-lo a despesa.
 
0 - Incluir TODO MUNDO do grupo
-1 - Finalizar seleção de pessoas
----------------------------------------
1 - Guilherme
2 - Rogerio
----------------------------------------
Escolha uma opção ou o número do participante: 0
Todos os membros do grupo foram incluídos!
Selecione a forma de divisão:
1 - Divisão Igual
2 - Divisão Por Porcentagem
Opção: 1
Despesa registrada e processada com sucesso!
=== MENU PRINCIPAL [Grupo: Viagem] ===
1 - Cadastrar Novo Membro no Grupo
2 - Adicionar Saldo a um Membro do Grupo
3 - Registrar Nova Despesa
4 - Visualizar Grupo
5 - Criar Grupo
6 - Mudar de Grupo
0 - Sair
Escolha uma opção: 4
=== SALDO ATUAL DO GRUPO ===
=================================================================
 GRUPO: VIAGEM                                               
=================================================================
ID         | NOME                      | SALDO INDIVIDUAL
-----------------------------------------------------------------
0          | Guilherme                 | 10600          
1          | Rogerio                   | 14400          
-----------------------------------------------------------------
SALDO TOTAL DO GRUPO:                  | R$ 250,00      
=================================================================
```
