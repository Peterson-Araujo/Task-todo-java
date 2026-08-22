package br.petersonaraujo.task_todo.ui;

import br.petersonaraujo.task_todo.exception.TaskException;
import br.petersonaraujo.task_todo.model.Status;
import br.petersonaraujo.task_todo.service.FileStorageService;
import br.petersonaraujo.task_todo.service.TaskService;
import br.petersonaraujo.task_todo.ui.dtos.TaskRequest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TodoApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FileStorageService fileStorageService = new FileStorageService();
        TaskService taskService = new TaskService(fileStorageService);
        boolean programaAtivo = true;

        System.out.println("Bem-vindo ao TodoApp!");

        while (programaAtivo) {

            System.out.println();
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Listar tarefas");
            System.out.println("3. Listar tarefas por status");
            System.out.println("4. Iniciar uma tarefa");
            System.out.println("5. Marcar tarefa como concluída");
            System.out.println("6. Deletar uma tarefa");
            System.out.println("7. Sair do programa");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("---------------------------------------------------------------");
                    System.out.print("Digite o nome da tarefa: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite a descrição da tarefa: ");
                    String descricao = sc.nextLine();

                    LocalDate dataFim = null;
                    boolean dataValida = false;

                    while (!dataValida) {
                        System.out.print("Digite a data de término da tarefa (yyyy-MM-dd): ");
                        String dataFimStr = sc.nextLine();

                        try {
                            dataFim = LocalDate.parse(dataFimStr);
                            dataValida = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Erro: Data inválida! Use o formato yyyy-MM-dd");
                        }
                    }

                    try {
                        TaskRequest taskRequest = new TaskRequest(
                                nome,
                                descricao,
                                dataFim
                        );

                        taskService.addTask(taskRequest.toModel(taskRequest));
                        System.out.println("Tarefa adicionada com sucesso!");
                        System.out.println("---------------------------------------------------------------");
                        break;

                    } catch (TaskException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Tarefas cadastradas:");
                    taskService.getAllTasks()
                            .forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------");
                    break;

                case 3:
                    System.out.println();
                    System.out.println("Escolha uma opção de status de tarefa para listar: ");
                    System.out.println("1. A_FAZER");
                    System.out.println("2. EM_ANDAMENTO");
                    System.out.println("3. CONCLUIDO");

                    int statusOption = sc.nextInt();
                    sc.nextLine();

                    switch (statusOption) {
                        case 1:
                            System.out.println("Tarefas A_FAZER:");
                            taskService.findByStatus(Status.A_FAZER)
                                    .forEach(System.out::println);
                            System.out.println("---------------------------------------------------------------");
                            break;
                        case 2:
                            System.out.println("Tarefas EM_ANDAMENTO:");
                            taskService.findByStatus(Status.EM_ANDAMENTO)
                                    .forEach(System.out::println);
                            System.out.println("---------------------------------------------------------------");
                            break;
                        case 3:
                            System.out.println("Tarefas CONCLUIDO:");
                            taskService.findByStatus(Status.CONCLUIDO)
                                    .forEach(System.out::println);
                            System.out.println("---------------------------------------------------------------");
                            break;
                        default:
                            System.out.println("Opção inválida. Voltando ao menu principal.");
                    }
                break;

                case 4:
                    System.out.println("---------------------------------------------------------------");
                    System.out.print("Digite o ID da tarefa que deseja iniciar: ");
                    Long idTask = sc.nextLong();
                    sc.nextLine();

                    boolean started = taskService.startTask(idTask);

                    if (started) {
                        System.out.println("Tarefa iniciada com sucesso!");
                    } else {
                        System.out.println("Não foi possível iniciar a tarefa. Verifique o ID informado.");
                    }
                    System.out.println("---------------------------------------------------------------");
                    break;

                case 5:
                    System.out.println("---------------------------------------------------------------");
                    System.out.print("Digite o ID da tarefa que deseja marcar como concluída: ");
                    Long id = sc.nextLong();
                    sc.nextLine();

                    boolean completed = taskService.completeTask(id);

                    if (completed) {
                        System.out.println("Tarefa marcada como concluída com sucesso!");
                    } else {
                        System.out.println("Não foi possível marcar a tarefa como concluída. Verifique o ID informado.");
                    }
                    System.out.println("---------------------------------------------------------------");
                    break;

                case 6:
                    System.out.println("---------------------------------------------------------------");
                    System.out.print("Digite o ID da tarefa que deseja deletar: ");
                    Long idToDelete = sc.nextLong();
                    sc.nextLine();

                    boolean deleted = taskService.deleteTask(idToDelete);
                    if (deleted) {
                        System.out.println("Tarefa deletada com sucesso!");
                    } else {
                        System.out.println("Não foi possível deletar a tarefa. Verifique o ID informado.");
                    }
                    System.out.println("---------------------------------------------------------------");
                    break;

                case 7:
                    programaAtivo = false;
                    System.out.println("Saindo do TodoApp. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        sc.close();
    }
}
