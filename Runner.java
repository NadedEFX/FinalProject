import java.util.Scanner;

public class Runner {
    private static TaskManager manager;
    
    public static void main(String [] args) {
        clearConsole();
        manager = new TaskManager();
        manager.loadFile("availableTasks.txt", "pendingTasks.txt");

        makeChoice();
    }

    public static void makeChoice(){
        Scanner sy = new Scanner(System.in);
        int choice;
        do{
            mainMenu();
            choice = InputValidator.getValidInteger("Enter your choice (1-4): ", 1, 4);
            System.out.println("You chose option "+ choice);
            menuChoice(choice);
            System.out.print("\u001B[32m" + "Press enter to continue " + "\u001B[0m");
            sy.nextLine();
            clearConsole();
        }while (choice != 4);
    }
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void menuChoice(int choice){
        switch (choice) {
            case 1:
                manager.loadTask();
                break;
            case 2:
                manager.deleteTask();
                break;
            case 3:
                manager.viewTasks();
                break;
            case 4:
                manager.savedFile("pendingTasks.txt", "availableTasks.txt");
                System.out.println("File is saved. Goodbye!");
                return;
        }
    }

    public static void mainMenu(){
        System.out.println("Choose an option: ");
        System.out.println("1. load a task");
        System.out.println("2. delete a task");
        System.out.println("3. view Tasks");
        System.out.println("4.exit");
    }
}