package br.petersonaraujo.task_todo.ui;

import br.petersonaraujo.task_todo.service.TaskService;
import br.petersonaraujo.task_todo.ui.dtos.TaskRequest;

import java.time.LocalDate;
import java.util.Scanner;

public class TodoApp {

    public static void main(String[] args) {

        final TaskService taskService = new TaskService();

        Scanner sc = new Scanner(System.in);

        boolean programaAtivo = true;

        while (programaAtivo) {
            System.out.println("Bem-vindo ao TodoApp!");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Listar tarefas");
            System.out.println("3. Marcar tarefa como concluída");
            System.out.println("4. Sair");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("---------------------------------------------------------------");
                    System.out.print("Digite o nome da tarefa: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite a descrição da tarefa: ");
                    String descricao = sc.nextLine();

                    System.out.print("Digite a data de término da tarefa (yyyy-MM-dd): ");
                    String dataFimStr = sc.nextLine();
                    LocalDate dataFim = LocalDate.parse(dataFimStr);

                    TaskRequest taskRequest = new TaskRequest(
                            nome,
                            descricao,
                            dataFim,
                            null
                    );
                    taskService.addTask(taskRequest.toModel(taskRequest));

                    System.out.println("Tarefa adicionada com sucesso!");

                    System.out.println("---------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Tarefas cadastradas:");
                    taskService.getAllTasks()
                            .forEach(
                                    task -> System.out.println(
                                            "- " + task.getNome()
                                            + " - Descrição: " + task.getDescricao()
                                            + " (" + task.getStatus() + ")"
                                            + " - Data de término: " + task.getDataFim()));
                    System.out.println("---------------------------------------------------------------");
                    break;
                case 3:
                    // Lógica para marcar tarefa como concluída
                    break;
                case 4:
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
