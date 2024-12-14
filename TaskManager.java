import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TaskManager {
    private ArrayList<String> pendingTasks = new ArrayList<>();
    private ArrayList<String> availableTasks = new ArrayList<>();
    private final int MAX_COMPLETED = 100;
    private String[] completedTasks = new String[MAX_COMPLETED];
    private int pendingTaskProgress = 0;
    private int completedTaskCount = 0;
    public static Scanner sy = new Scanner(System.in);
    
    public void savedFile(String completedFile, String pendingFile){
        try(BufferedWriter writer= new BufferedWriter(new FileWriter(completedFile))){
            for(int i = 0; i < completedTaskCount; i++){
                writer.write(completedTasks[i]);
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Error completing tasks:"+ e);
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter (pendingFile))){
            for(int i=0; i<pendingTaskProgress; i++){
                writer.write(pendingTasks.get(i));
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Error while pending task:" + e);
        }
    }
    
    public void loadFile(String completedFile, String pendingFile){
    try (BufferedReader reader = new BufferedReader(new FileReader(pendingFile))){
        String line;
        while((line=reader.readLine()) !=null){
            pendingTasks.add(line);
        }
    }catch(IOException e){
        System.out.println("Error loading Pending Tasks:"+ e);
    }
    int completedTaskIndex = 0;
    try(BufferedReader reader = new BufferedReader(new FileReader(completedFile))){
        String line;
        while((line=reader.readLine())!= null){
            completedTasks[completedTaskIndex] = line;
            completedTaskIndex++;
        }
    }catch(IOException e){
        System.out.println("Error While Loading pending tasks:" + e);
    }
    }

    public void loadTask(){
        System.out.print("What task do you want to add: ");
        String task = sy.nextLine();
        availableTasks.add(task);
    }

    public void deleteTask() throws IndexOutOfBoundsException {
        if(availableTasks.isEmpty()){
            System.out.println("No task availble to delete.");
            return;
        }

        viewTasks();
        int taskIndex = InputValidator.getValidInteger(
            "Enter the index of the task to delete: ",0, availableTasks.size() - 1
        );

        if(pendingTaskProgress >= pendingTasks.size()) {
            System.out.println("Task isn't complete yet.");
            return;
        }
        
        pendingTasks.add(availableTasks.remove(taskIndex));
        System.out.println(pendingTasks.get(pendingTaskProgress-1)+ "Task successfully completed");
    }
    public void viewTasks(){
        System.out.println("Available tasks: "+ availableTasks);
        System.out.print("Completed Tasks: ");
        for (int i = 0; i < pendingTaskProgress; i++) {
            System.out.print(pendingTasks.get(i) + " ");
        }
        System.out.println();
    }
}