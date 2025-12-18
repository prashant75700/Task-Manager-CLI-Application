package com.example.taskmanager;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class TaskManager {
    private ArrayList<Task> tasks;
    private int nextId;

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
        // Add some dummy tasks for testing
        addTask("Learn Spring Boot", "Understand how to build a REST API", "HIGH");
        addTask("Build React Frontend", "Create a beautiful UI with Vite", "MEDIUM");
    }

    public Task addTask(String title, String description, String priority) {
        Task newTask = new Task(nextId++, title, description, priority);
        tasks.add(newTask);
        return newTask;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int id) {
        for(Task task : tasks) {
            if (task.getID() == id) {
                return task;
            }
        }
        return null;
    }

    public Task updateTask(int id, String newTitle, String newDescription, String newPriority) {
        Task task = getTaskById(id);
        if (task != null) {
            if (newTitle != null) task.setTitle(newTitle);
            if (newDescription != null) task.setDescription(newDescription);
            if (newPriority != null) task.setPriority(newPriority);
            return task;
        }
        return null;
    }

    public Task setTaskCompletion(int id, boolean completed) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setCompleted(completed);
            return task;
        }
        return null;
    }

    public Task toggleTaskCompletion(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
            return task;
        }
        return null;
    }

    public boolean deleteTask(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            return true;
        }
        return false;
    }

    public List<Task> searchByPriority(String priority) {
        ArrayList<Task> results = new ArrayList<>();
        String searchPriority = priority.toUpperCase();
        for (Task task : tasks) {
            if (task.getPriority().equals(searchPriority)) {
                results.add(task);
            }
        }
        return results;
    }
    
    public Map<String, Object> getStats() {
        int completed = 0;
        int high = 0, medium = 0, low = 0;

        for (Task task : tasks) {
            if (task.isCompleted()) completed++;
            switch (task.getPriority()) {
                case "HIGH": high++; break;
                case "MEDIUM": medium++; break;
                case "LOW": low++; break;
            }
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("total", tasks.size());
        stats.put("completed", completed);
        stats.put("pending", tasks.size() - completed);
        stats.put("high", high);
        stats.put("medium", medium);
        stats.put("low", low);
        return stats;
    }

    private int getPriorityValue (String priority) {
        switch (priority) {
            case "HIGH": return 3;
            case "MEDIUM": return 2;
            case "LOW": return 1;
            default: return 0;
        }
    }
}