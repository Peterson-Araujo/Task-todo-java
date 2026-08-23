package br.petersonaraujo.task_todo.service;

import br.petersonaraujo.task_todo.exception.TaskException;
import br.petersonaraujo.task_todo.model.Status;
import br.petersonaraujo.task_todo.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    private final List<Task> tasks = new ArrayList<>();

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private TaskService taskService;

    Task firstTask;
    Task secondTask;
    Task thirdTask;

    @BeforeEach
    void setUp() {
        when(fileStorageService.load())
                .thenReturn(new ArrayList<>());

        taskService = new TaskService(fileStorageService);

        firstTask = new Task("Task 1", "Descricao 1", null, LocalDate.now().plusDays(1), null);
        secondTask = new Task("Task 2", "Descricao 2", null, LocalDate.now().plusDays(1), null);
        thirdTask = new Task("Task 3", "Descricao 3", null, LocalDate.now().plusDays(1), null);
    }

    @Test
    @DisplayName("Quando for adicionado uma nova task, deverá retornar a lista preenchida")
    void whenAddNewTask_ThenReturnTask() {

        taskService.addTask(firstTask);

        assertEquals(1L, firstTask.getId());

        verify(fileStorageService).save(anyList());
    }

    // =========================================================
    // ADD TASK
    // =========================================================

    @Test
    void shouldAddTaskSuccessfully() {
        firstTask.setDataFim(LocalDate.now().plusDays(1));

        taskService.addTask(firstTask);

        assertEquals(1L, firstTask.getId());
        assertEquals(1, taskService.getAllTasks().size());
        assertTrue(taskService.getAllTasks().contains(firstTask));

        verify(fileStorageService).save(anyList());
    }

    @Test
    void shouldThrowExceptionWhenTaskEndDateIsInThePast() {
        firstTask.setDataFim(LocalDate.now().minusDays(1));

        TaskException exception = assertThrows(
                TaskException.class,
                () -> taskService.addTask(firstTask)
        );

        assertEquals(
                "Error: Task end date cannot be in the past",
                exception.getMessage()
        );

        assertTrue(taskService.getAllTasks().isEmpty());

        verify(fileStorageService, never()).save(anyList());
    }

    @Test
    void shouldGenerateSequentialTaskIds() {
        firstTask.setDataFim(LocalDate.now().plusDays(1));

        secondTask.setDataFim(LocalDate.now().plusDays(2));

        taskService.addTask(firstTask);
        taskService.addTask(secondTask);

        assertEquals(1L, firstTask.getId());
        assertEquals(2L, secondTask.getId());

        assertEquals(2, taskService.getAllTasks().size());

        verify(fileStorageService, times(2)).save(anyList());
    }

    // =========================================================
    // GET ALL TASKS
    // =========================================================

    @Test
    void shouldReturnAllTasks() {
        firstTask.setId(1L);

        secondTask.setId(2L);

        when(fileStorageService.load())
                .thenReturn(new ArrayList<>(List.of(firstTask, secondTask)));

        taskService = new TaskService(fileStorageService);

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        assertEquals(firstTask, tasks.get(0));
        assertEquals(secondTask, tasks.get(1));
    }

    // =========================================================
    // FIND BY STATUS
    // =========================================================

    @Test
    void shouldFindTasksByStatus() {
        firstTask.setId(1L);
        firstTask.setStatus(Status.A_FAZER);

        secondTask.setId(2L);
        secondTask.setStatus(Status.CONCLUIDO);

        thirdTask.setId(3L);
        thirdTask.setStatus(Status.A_FAZER);

        when(fileStorageService.load())
                .thenReturn(new ArrayList<>(
                        List.of(firstTask, secondTask, thirdTask)
                ));

        taskService = new TaskService(fileStorageService);

        List<Task> result = taskService.findByStatus(Status.A_FAZER);

        assertEquals(2, result.size());
        assertTrue(result.contains(firstTask));
        assertTrue(result.contains(thirdTask));
        assertFalse(result.contains(secondTask));
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoTasksWithStatus() {
        firstTask.setId(1L);
        firstTask.setStatus(Status.CONCLUIDO);

        when(fileStorageService.load())
                .thenReturn(new ArrayList<>(List.of(firstTask)));

        taskService = new TaskService(fileStorageService);

        List<Task> result = taskService.findByStatus(Status.A_FAZER);

        assertTrue(result.isEmpty());
    }

    // =========================================================
    // FIND BY ID
    // =========================================================

    @Test
    void shouldFindTaskById() {
        firstTask.setId(1L);

        when(fileStorageService.load())
                .thenReturn(new ArrayList<>(List.of(firstTask)));

        taskService = new TaskService(fileStorageService);

        Task result = taskService.findById(1L);

        assertEquals(firstTask, result);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {
        when(fileStorageService.load())
                .thenReturn(new ArrayList<>());

        taskService = new TaskService(fileStorageService);

        TaskException exception = assertThrows(
                TaskException.class,
                () -> taskService.findById(1L)
        );

        assertEquals(
                "Error: Task not found: 1",
                exception.getMessage()
        );
    }

    // =========================================================
    // DELETE TASK
    // =========================================================

    @Test
    void shouldDeleteTaskSuccessfully() {
        firstTask.setId(1L);

        when(fileStorageService.load())
                .thenReturn(new ArrayList<>(List.of(firstTask)));

        taskService = new TaskService(fileStorageService);

        boolean result = taskService.deleteTask(1L);

        assertTrue(result);
        assertTrue(taskService.getAllTasks().isEmpty());

        verify(fileStorageService).save(anyList());
    }

    @Test
    void shouldReturnFalseWhenDeletingTaskThatDoesNotExist() {
        when(fileStorageService.load())
                .thenReturn(new ArrayList<>());

        taskService = new TaskService(fileStorageService);

        boolean result = taskService.deleteTask(1L);

        assertFalse(result);

        verify(fileStorageService, never()).save(anyList());
    }

    // =========================================================
    // COMPLETE TASK
    // =========================================================

    @Test
    void shouldCompleteTaskSuccessfully() {
        firstTask.setId(1L);
        firstTask.setDataFim(LocalDate.now().plusDays(5));

        when(fileStorageService.load())
                .thenReturn(new ArrayList<>(List.of(firstTask)));

        taskService = new TaskService(fileStorageService);

        boolean result = taskService.completeTask(1L);

        assertTrue(result);
        assertEquals(LocalDate.now(), firstTask.getDataFim());

        verify(fileStorageService).save(anyList());
    }

    @Test
    void shouldReturnFalseWhenCompletingTaskThatDoesNotExist() {
        when(fileStorageService.load())
                .thenReturn(new ArrayList<>());

        taskService = new TaskService(fileStorageService);

        boolean result = taskService.completeTask(1L);

        assertFalse(result);

        verify(fileStorageService, never()).save(anyList());
    }

    // =========================================================
    // START TASK
    // =========================================================

    @Test
    void shouldStartTaskSuccessfully() {
        firstTask.setId(1L);

        when(fileStorageService.load())
                .thenReturn(new ArrayList<>(List.of(firstTask)));

        taskService = new TaskService(fileStorageService);

        boolean result = taskService.startTask(1L);

        assertTrue(result);
        assertEquals(LocalDate.now(), firstTask.getDataInicio());

        verify(fileStorageService).save(anyList());
    }

    @Test
    void shouldReturnFalseWhenStartingTaskThatDoesNotExist() {
        when(fileStorageService.load())
                .thenReturn(new ArrayList<>());

        taskService = new TaskService(fileStorageService);

        boolean result = taskService.startTask(1L);

        assertFalse(result);

        verify(fileStorageService, never()).save(anyList());
    }
}