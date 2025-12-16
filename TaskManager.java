import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskManager {
    private ArrayList<Task> tasks;
    private int nextId;

    //Constructor era 
    public TaskManager () {
        tasks = new ArrayList<>(); //it creates new ArrayList
        nextId = 1;
    }

    // Now here we start the Method Part which help us to do certain tasks :) 

    //CREATE: Adding new task with it:
    public void addTask(String title, String description, String priority) {
        Task newTask = new Task(nextId++, title, description, priority);
        tasks.add(newTask);
        System.out.println("Task added successfully! ( ID: " + newTask.getID() + " )");
    }

    //READ: with this we can view all Tasks :)
    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No Tasks Available. -_- ");
        }

        System.out.println("\n======= ALL TASKS =======");
        for (Task task : tasks) { //enhanced for loop
            System.out.println(task);
            System.out.println("----------------------------");
        }
        System.out.println("Total tasks: " + tasks.size()); //one of the inbuilt method of ArrayList same as isEmpty
    }

    //READ: view single task by it's ID
    public void viewTask(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            System.out.println("\n" + task);
        }
        else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    //Helper method for Find task by Id:
    private Task findTaskById(int id) {
        for(Task task : tasks) {
            if (task.getID() == id) {
                return task;
            }
        }
        return null;
    }

    //UPDATE: here we can Modify our Tasks Details
    public void updateTask(int id, String newTitle, String newDescription, String newPriority) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setPriority(newPriority);
            System.out.println("Task updated Successfuly!!!");
        }
        else {
            System.out.println("Tasl with ID " + id + " not found.");
        }
    }

    //UPDATE: with this we update the status of our task ( completed or not)
    public void markComplete(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setCompleted(true);
            System.out.println("Task marked as Complete!!!");
        }
        else {
            System.out.println("Task with ID " + id + " not found");
        }
    }

    //DELETE: here we can Remove the task
    public void deleteTask(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Task Deleted successfuly ");
        }
        else {
            System.out.println("Task with ID " + id + " not found");
        }
    }
}