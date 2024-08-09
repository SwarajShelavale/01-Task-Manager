package com.taskmanager;

import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskManagerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testAddTask() {
        Task task = new Task();
        task.setName("Integration Test Task");
        task.setDescription("Integration Test Description");

        ResponseEntity<Task> response = restTemplate.postForEntity("/api/task", task, Task.class);

        // Check if the status code is 201 (CREATED)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Check if the response body is not null
        assertNotNull(response.getBody());

        // Check if the name of the created task matches the expected name
        assertEquals("Integration Test Task", response.getBody().getName());
    }
}
