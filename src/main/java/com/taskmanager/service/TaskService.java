package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTaskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setName(updatedTaskDetails.getName());
        task.setDescription(updatedTaskDetails.getDescription());
        task.setStatus(updatedTaskDetails.isStatus());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
