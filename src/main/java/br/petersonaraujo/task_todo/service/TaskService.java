package br.petersonaraujo.task_todo.service;

import br.petersonaraujo.task_todo.exception.TaskException;
import br.petersonaraujo.task_todo.model.Status;
import br.petersonaraujo.task_todo.model.Task;

import java.time.LocalDate;
import java.util.List;

public class TaskService {

    private final List<Task> tasks;
    private final FileStorageService fileStorageService;

    public TaskService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
        this.tasks = fileStorageService.load();
    }

    public void addTask(Task task) {
        if (task.getDataFim().isBefore(LocalDate.now())) {
            throw new TaskException("Error: Task end date cannot be in the past");
        }

        task.setId((long) (tasks.size() + 1));
        tasks.add(task);
        fileStorageService.save(tasks);
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
                        new TaskException("Error: Task not found: " + id));
    }

    public boolean deleteTask(Long id) {
        try {
            Task task = findById(id);
            tasks.remove(task);
            fileStorageService.save(tasks);
            return true;
        } catch (TaskException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean completeTask(Long id) {
        try {
            Task task = findById(id);
            task.setDataFim(LocalDate.now());
            task.markAsDone();
            fileStorageService.save(tasks);
            return true;
        } catch (TaskException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean startTask(Long id) {
        try {
            Task task = findById(id);
            task.setDataInicio(LocalDate.now());
            task.start();
            fileStorageService.save(tasks);
            return true;
        } catch (TaskException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
