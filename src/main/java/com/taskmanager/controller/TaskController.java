package com.taskmanager.controller;


import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/test")
    public String testData() {
        return "YES WORKING";
    }
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task task = taskService.getTaskById(id).orElseThrow();
        return ResponseEntity.ok(task);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task newTaskDetails) {
        Task updatedTask  = taskService.updateTask(id,newTaskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
