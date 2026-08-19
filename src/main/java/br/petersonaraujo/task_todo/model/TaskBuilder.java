package br.petersonaraujo.task_todo.model;

import java.time.LocalDate;

public class TaskBuilder {

    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Status status;
    private String atribuido;

    public TaskBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public TaskBuilder setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public TaskBuilder setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public TaskBuilder setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public TaskBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public TaskBuilder setAtribuido(String atribuido) {
        this.atribuido = atribuido;
        return this;
    }

    public Task build() {
        return new Task(nome, descricao, dataInicio, dataFim, status, atribuido);
    }
}
