package br.petersonaraujo.task_todo.ui.dtos;

import br.petersonaraujo.task_todo.model.Status;
import br.petersonaraujo.task_todo.model.Task;

import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String nome,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        Status status,
        String atribuido
) {
}
