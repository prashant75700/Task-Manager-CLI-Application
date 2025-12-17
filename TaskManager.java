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

    // SEARCH: Find tasks by Priority
    public void searchByPriority(String priority) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                results.add(task);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No tasks found with priority");
        }
        else {
            System.out.println("\n======= TASKS WITH PRIORITY: " + priority + " =======");
            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("------------------------------");
            }
        }
    }

    // Filter: show only pendingg tasks
    public void viewPendingTasks() {
        ArrayList<Task> pending = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pending.add(task);
            }
        }
        if (pending.isEmpty()) {
            System.out.println("No pending tasks here");
        }
        else {
            System.out.println("\n======= PENDING TASKS =======");

            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("------------------------------");
            }
        }
    }

    //SORT: sorting task by priority  ( HIGH -> MEDIUM -> LOW )

    //first of all we have to made a helper method for our sorting method ( we can't ruin our mind again as we F****dup with the findTaskById )

    private int getPriorityValue (String priority) {
        switch (priority) {
            
            case "HIGH": 
                return 3;

            case "MEDIUM":
                return 2;

            case "LOW": 
                return 1;
        
            default:
                return 0;
        }
    }

    //Now our sorting method arrived here 
    public void sortByPriority() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                int priority1 = getPriorityValue(t1.getPriority());
                int priority2 = getPriorityValue(t2.getPriority());
                return Integer.compare(priority2, priority1); // using Descending order 
            }
        });
        System.out.println("Tasks sorted by priority!");
        viewAllTasks();
    }

    //STATS: it'll help us to Show Statics of our tasks
    public void showStats() {
        int completed = 0;
        int high = 0, medium = 0, low = 0;

        for ( Task task : tasks) {
            if (task.isCompleted()) completed++;

            switch (task.getPriority()) {
                case "HIGH": high++;
                    break;
                case "MEDIUM": medium++;
                    break;
                case "LOW": low++;
                    break;
            }
        }

        System.out.println("\n========== TASK STATISTICS ==========");
        System.out.println("Total Tasks: " + tasks.size());
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + (tasks.size() - completed));
        System.out.println("\nPriority Breakdown:");
        System.out.println("  HIGH: " + high);
        System.out.println("  MEDIUM: " + medium);
        System.out.println("  LOW: " + low);
    }
}