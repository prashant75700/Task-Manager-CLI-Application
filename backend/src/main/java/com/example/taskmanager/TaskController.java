package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // Allow all for development
public class TaskController {

    private final TaskManager taskManager;

    @Autowired
    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskManager.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Task task = taskManager.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody TaskRequest request) {
        Task createdTask = taskManager.addTask(request.getTitle(), request.getDescription(), request.getPriority());
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody TaskRequest request) {
        Task updatedTask = taskManager.updateTask(id, request.getTitle(), request.getDescription(), request.getPriority());
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        if (taskManager.deleteTask(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> markComplete(@PathVariable int id) {
        Task task = taskManager.setTaskCompletion(id, true);
        if (task != null) return ResponseEntity.ok(task);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/unmark")
    public ResponseEntity<Task> unmarkComplete(@PathVariable int id) {
        Task task = taskManager.setTaskCompletion(id, false);
        if (task != null) return ResponseEntity.ok(task);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<Task> toggleComplete(@PathVariable int id) {
        Task task = taskManager.toggleTaskCompletion(id);
        if (task != null) return ResponseEntity.ok(task);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return taskManager.getStats();
    }

    // DTO for requests
    public static class TaskRequest {
        private String title;
        private String description;
        private String priority;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getPriority() { return priority; }
        public void setPriority(String priority) { this.priority = priority; }
    }
}
