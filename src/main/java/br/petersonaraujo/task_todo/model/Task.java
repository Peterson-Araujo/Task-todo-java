package br.petersonaraujo.task_todo.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Status status;

    public Task(String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
    }

    public void markAsDone() {
        this.status = Status.CONCLUIDO;
    }

    public void start() {
        this.status = Status.EM_ANDAMENTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task: "
                + "id = " + id
                +", nome = '" + nome
                + '\'' +", descricao = '" + descricao
                + '\'' +", dataInicio = " + dataInicio
                + ", dataFim = " + dataFim
                +", status atual = " + status;
    }
}
