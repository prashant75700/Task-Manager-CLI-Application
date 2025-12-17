import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("====================================");
        System.out.println("||   TASK MANAGEMENT SYSTEM v1.O   ||");
        System.out.println("====================================");

        while (running) {
            showMenu();
            System.out.println("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  //// it consume new line
            switch (choice) {
                case 1:  // Add Task
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter priority (HIGH/MEDIUM/LOW): ");
                    String priority = sc.nextLine();
                    manager.addTask(title, desc, priority);
                    break;
                    
                case 2:  // View All Tasks
                    manager.viewAllTasks();
                    break;
                    
                case 3:  // View Single Task
                    System.out.print("Enter task ID: ");
                    int viewId = sc.nextInt();
                    manager.viewTask(viewId);
                    break;
                    
                case 4:  // Update Task
                    System.out.print("Enter task ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("Enter new description: ");
                    String newDesc = sc.nextLine();
                    System.out.print("Enter new priority: ");
                    String newPriority = sc.nextLine();
                    manager.updateTask(updateId, newTitle, newDesc, newPriority);
                    break;
                    
                case 5:  // Mark Complete
                    System.out.print("Enter task ID to mark complete: ");
                    int completeId = sc.nextInt();
                    manager.markComplete(completeId);
                    break;
                    
                case 6:  // Delete Task
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = sc.nextInt();
                    manager.deleteTask(deleteId);
                    break;
                    
                case 7:  // Search by Priority
                    System.out.print("Enter priority (HIGH/MEDIUM/LOW): ");
                    String searchPriority = sc.nextLine();
                    manager.searchByPriority(searchPriority);
                    break;
                    
                case 8:  // View Pending Tasks
                    manager.viewPendingTasks();
                    break;
                    
                case 9:  // Sort by Priority
                    manager.sortByPriority();
                    break;
                    
                case 10:  // Show Stats
                    manager.showStats();
                    break;
                    
                case 0:  // Exit
                    System.out.println("Thanks for using Task Manager. Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid choice! Try again.");
            
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                sc.nextLine();
            }
        
        
        sc.close();
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=====================================");
        System.out.println("||        MAIN MENU       ||");
        System.out.println("=====================================");
        System.out.println("║ 1.  Add Task                      ║");
        System.out.println("║ 2.  View All Tasks                ║");
        System.out.println("║ 3.  View Single Task              ║");
        System.out.println("║ 4.  Update Task                   ║");
        System.out.println("║ 5.  Mark Task as Complete         ║");
        System.out.println("║ 6.  Delete Task                   ║");
        System.out.println("║ 7.  Search by Priority            ║");
        System.out.println("║ 8.  View Pending Tasks            ║");
        System.out.println("║ 9.  Sort by Priority              ║");
        System.out.println("║ 10. Show Statistics               ║");
        System.out.println("║ 0.  Exit                          ║");
        System.out.println("=====================================");

    }
}
