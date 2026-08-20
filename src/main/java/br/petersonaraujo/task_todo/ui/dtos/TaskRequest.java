package br.petersonaraujo.task_todo.ui.dtos;

import br.petersonaraujo.task_todo.model.Status;
import br.petersonaraujo.task_todo.model.Task;

import java.time.LocalDate;

public class TaskRequest {

    private String nome;
    private String descricao;
    private LocalDate dataFim;

    public TaskRequest(String nome, String descricao, LocalDate dataFim) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataFim = dataFim;
    }

    public Task toModel(TaskRequest taskRequest) {
        if (taskRequest.getNome() == null || taskRequest.getDescricao() == null || taskRequest.getDataFim() == null) {
            throw new IllegalArgumentException("Nome, descrição e data de fim são obrigatórios.");
        }

        return new Task(
                taskRequest.getNome(),
                taskRequest.getDescricao(),
                null, // dataInicio será definido quando a tarefa for iniciada
                taskRequest.getDataFim(),
                Status.A_FAZER
        );
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
}
