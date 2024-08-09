package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setId(1L);
        task.setName("Test Task");
        task.setDescription("Task Description");
        task.setStatus(false);
    }

    @Test
    void testAddTask() {
        when(taskRepository.save(task)).thenReturn(task);
        Task createdTask = taskService.addTask(task);
        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getName());
    }
    @Test
    void testGetTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> retrievedTask = taskService.getTaskById(1L);
        assertTrue(retrievedTask.isPresent());
        assertEquals("Test Task", retrievedTask.get().getName());
    }

    @Test
    void testDeleteTask() {
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

}
