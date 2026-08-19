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
    }

    public void addTask(Task task) {
        task.setId((long) (tasks.size() + 1));
        task.setStatus(Status.A_FAZER);
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

    public void deleteTask(Long id) {
        Task task = findById(id);
        tasks.remove(task);
    }

    public void completeTask(Long id) {
        Task task = findById(id);
        task.markAsDone();
    }

    public void startTask(Long id) {
        Task task = findById(id);
        task.setDataInicio(LocalDate.now());
        task.start();
    }
}
