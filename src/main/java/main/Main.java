package main;

import domain.model.Grupo;
import domain.model.Membro;
import domain.strategy.Divisao;
import domain.strategy.DivisaoIgual;
import domain.strategy.DivisaoPorPorcentagem;
import domain.value_objects.Dinheiro;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static int quantGrupo = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Grupo> todosOsGrupos = new HashMap<>();
        Grupo grupoAtual = null;

        System.out.println("=== BEM-VINDO AO SISTEMA DE DIVISÃO DE DESPESAS ===");

        while (true) {
            if (grupoAtual == null) {
                System.out.println("Você ainda não possui nenhum grupo cadastrado.");
                System.out.println("1 - Criar Novo Grupo");
                System.out.println("0 - Sair do Sistema");
                System.out.print("Escolha uma opção obrigatória: ");

                int opcaoInicial = lerInteiro(scanner);

                if (opcaoInicial == 1) {
                    grupoAtual = criarESalvarGrupo(scanner, todosOsGrupos);
                    System.out.println("Grupo '" + grupoAtual.getNome() + "' criado com sucesso!");
                } else if (opcaoInicial == 0) {
                    break;
                } else {
                    System.out.println("Opção inválida! Você precisa criar um grupo para continuar.");
                }

                continue;
            }

            System.out.println("=== MENU PRINCIPAL [Grupo: " + grupoAtual.getNome() + "] ===");
            System.out.println("1 - Cadastrar Novo Membro no Grupo");
            System.out.println("2 - Adicionar Saldo a um Membro do Grupo");
            System.out.println("3 - Registrar Nova Despesa");
            System.out.println("4 - Visualizar Grupo");
            System.out.println("5 - Criar Grupo");
            System.out.println("6 - Mudar de Grupo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do novo membro: ");
                    String nomeMembro = scanner.nextLine();
                    grupoAtual.adicionarMembro(new Membro(Integer.toString(grupoAtual.getQuantidadeDeMembros()) ,nomeMembro));
                    System.out.println(nomeMembro + " adicionado ao grupo!");
                    break;

                case 2:
                    System.out.println("Digite o ID do membro: ");
                    String idMembro = scanner.nextLine();
                    if (grupoAtual.buscarMembroPorId(idMembro) == null) {
                        System.out.println("Não Existe esse ID nesse grupo");
                        break;
                    }
                    System.out.println("Digite o saldo desejado: ");
                    int saldoMais = scanner.nextInt();
                    Dinheiro novoSaldo = new Dinheiro(saldoMais);
                    scanner.nextLine();
                    grupoAtual.buscarMembroPorId(idMembro).adicionarAoSaldo(novoSaldo);
                    System.out.println(grupoAtual.buscarMembroPorId(idMembro).getNome() + " Agora tem um saldo de: " + grupoAtual.buscarMembroPorId(idMembro).getSaldoEmCentavos());
                    break;

                case 3:
                    if (grupoAtual.getCadaMembro().isEmpty()) {
                        System.out.println("Não é possível registrar despesas. O grupo não tem membros ainda!");
                        break;
                    }

                    System.out.print("Digite o valor total da despesa: ");
                    int valorDuplo = scanner.nextInt();
                    scanner.nextLine();

                    Dinheiro valorGasto = new Dinheiro(valorDuplo);

                    System.out.println("Quem pagou a despesa?");
                    List<Membro> membros = grupoAtual.getCadaMembro();
                    for (int i = 0; i < membros.size(); i++) {
                        System.out.printf("[ %d ] %s\n", (i + 1), membros.get(i).getNome());
                    }
                    System.out.print("Escolha o número do pagador: ");
                    int indicePagador = lerInteiro(scanner) - 1;

                    if (indicePagador < 0 || indicePagador >= membros.size()) {
                        System.out.println("Pagador inválido. Operação cancelada.");
                        break;
                    }
                    String pagadorId = membros.get(indicePagador).getId();

                    List<Membro> participantesDaDespesa = new ArrayList<>();

                    System.out.println("=== QUEM VAI DIVIDIR ESSA CONTA? ===");
                    System.out.println("Digite o ID do membro para adicionar-lo a despesa.");
                    System.out.println(" ");
                    System.out.println("0 - Incluir TODO MUNDO do grupo");
                    System.out.println("-1 - Finalizar seleção de pessoas");
                    System.out.println("----------------------------------------");
                    for (int i = 0; i < membros.size(); i++) {
                        System.out.printf("%d - %s\n", (i + 1), membros.get(i).getNome());
                    }
                    System.out.println("----------------------------------------");

                    while (true) {
                        System.out.print("Escolha uma opção ou o número do participante: ");
                        int opParticipante = lerInteiro(scanner);

                        if (opParticipante == 0) {
                            participantesDaDespesa = new ArrayList<>(membros);
                            System.out.println("Todos os membros do grupo foram incluídos!");
                            break;
                        }

                        if (opParticipante == -1) {
                            if (participantesDaDespesa.isEmpty()) {
                                System.out.println("Você precisa selecionar pelo menos uma pessoa!");
                                continue;
                            }
                            break;
                        }

                        if (opParticipante > 0 && opParticipante <= membros.size()) {
                            Membro selecionado = membros.get(opParticipante - 1);
                            if (!participantesDaDespesa.contains(selecionado)) {
                                participantesDaDespesa.add(selecionado);
                                System.out.printf("%s adicionado(a).\n", selecionado.getNome());
                            } else {
                                System.out.println("Essa pessoa já foi adicionada!");
                            }
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    }

                    System.out.println("Selecione a forma de divisão:");
                    System.out.println("1 - Divisão Igual");
                    System.out.println("2 - Divisão Por Porcentagem");
                    System.out.print("Opção: ");
                    int opEstrategia = lerInteiro(scanner);

                    Divisao estrategia;

                    if (opEstrategia == 2) {
                        Map<String, Double> porcentagensMap = new HashMap<>();
                        System.out.println("Digite a porcentagem para cada participante (Ex: 50 para 50%):");

                        for (Membro participante : participantesDaDespesa) {
                            System.out.print("Porcentagem para " + participante.getNome() + ": ");
                            double porcentagem = scanner.nextDouble();
                            scanner.nextLine();
                            porcentagensMap.put(participante.getId(), porcentagem);
                        }

                        estrategia = new DivisaoPorPorcentagem(porcentagensMap);
                    } else {
                        estrategia = new DivisaoIgual();
                    }

                    List<String> participantesIds = new ArrayList<>();
                    for (Membro u : participantesDaDespesa) {
                        participantesIds.add(u.getId());
                    }

                    try {
                        grupoAtual.registrarDespesa(pagadorId, valorGasto, participantesIds, estrategia);
                        System.out.println("Despesa registrada e processada com sucesso!");
                    } catch (Exception e) {
                        System.err.println("Erro ao processar despesa: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("=== SALDO ATUAL DO GRUPO ===");
                    grupoAtual.exibirSaldos();
                    break;

                case 5:
                    grupoAtual = criarESalvarGrupo(scanner, todosOsGrupos);
                    break;

                case 6:
                    if (todosOsGrupos.size() <= 1) {
                        System.out.println("Você só possui um grupo cadastrado. Crie outro primeiro (Opção 4).");
                        break;
                    }

                    System.out.println("=== SELECIONE O GRUPO PARA ALTERNAR ===");
                    List<String> nomesDosGrupos = new ArrayList<>(todosOsGrupos.keySet());

                    for (int i = 0; i < nomesDosGrupos.size(); i++) {
                        System.out.printf("%d - %s\n", (i + 1), nomesDosGrupos.get(i));
                    }
                    System.out.print("Escolha o número do grupo desejado: ");
                    int indiceGrupo = lerInteiro(scanner) - 1;

                    if (indiceGrupo >= 0 && indiceGrupo < nomesDosGrupos.size()) {
                        String nomeSelecionado = nomesDosGrupos.get(indiceGrupo);

                        grupoAtual = todosOsGrupos.get(nomeSelecionado);
                        System.out.println("Você mudou para o grupo: " + grupoAtual.getNome());
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static Grupo criarESalvarGrupo(Scanner scanner, Map<String, Grupo> todosOsGrupos) {
        while (true) {
            System.out.print("Digite o nome do novo grupo (ex: Viagem, Republica): ");
            String nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("O nome do grupo não pode ser vazio.");
                continue;
            }

            if (todosOsGrupos.containsKey(nome.toLowerCase())) {
                System.out.println("Já existe um grupo com este nome! Escolha outro.");
                continue;
            }

            Grupo novoGrupo = new Grupo(Integer.toString(quantGrupo) ,nome);
            todosOsGrupos.put(nome.toLowerCase(), novoGrupo);
            System.out.println("Grupo '" + nome + "' criado e salvo com sucesso!");
            return novoGrupo;
        }
    }

    private static int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, digite um número válido.");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }
}