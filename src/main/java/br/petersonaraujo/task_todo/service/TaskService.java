package br.petersonaraujo.task_todo.service;

import br.petersonaraujo.task_todo.exception.TaskException;
import br.petersonaraujo.task_todo.model.Status;
import br.petersonaraujo.task_todo.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
        preencherDados();
    }

    public void addTask(Task task) {
        task.setId((long) (tasks.size() + 1));
        tasks.add(task);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> findByStatus(Status status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    public Task findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new TaskException("Task not found: " + id));
    }

    public boolean deleteTask(Long id) {
        try {
            Task task = findById(id);
            tasks.remove(task);
            return true;
        } catch (TaskException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean completeTask(Long id) {
        try {
            Task task = findById(id);
            task.setDataFim(LocalDate.now());
            task.markAsDone();
            return true;
        } catch (TaskException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean startTask(Long id) {
        try {
            Task task = findById(id);
            task.setDataInicio(LocalDate.now());
            task.start();
            return true;
        } catch (TaskException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    // Método para preencher a lista de tarefas com dados fictícios
    private void preencherDados() {
        addTask(new Task("Tarefa 1", "Descrição da tarefa 1", null, LocalDate.now().plusDays(5), Status.A_FAZER));
        addTask(new Task("Tarefa 2", "Descrição da tarefa 2", null, LocalDate.now().plusDays(3), Status.A_FAZER));
        addTask(new Task("Tarefa 3", "Descrição da tarefa 3", null, LocalDate.now().plusDays(7), Status.A_FAZER));
        addTask(new Task("Tarefa 4", "Descrição da tarefa 4", null, LocalDate.now().plusDays(2), Status.A_FAZER));
        addTask(new Task("Tarefa 5", "Descrição da tarefa 5", null, LocalDate.now().plusDays(10), Status.A_FAZER));
    }
}
