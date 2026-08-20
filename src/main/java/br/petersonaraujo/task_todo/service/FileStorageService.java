package br.petersonaraujo.task_todo.service;

import br.petersonaraujo.task_todo.exception.TaskException;
import br.petersonaraujo.task_todo.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorageService {

    private static final String FILE_PATH = "data/tasks.dat";

    public void save(List<Task> tasks) throws TaskException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            output.writeObject(tasks);
            output.flush();
        } catch (IOException e) {
            throw new TaskException("Error saving tasks", e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Task> load() throws TaskException {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }

            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
                return (List<Task>) input.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new TaskException("Error loading tasks", e);
        }
    }
}