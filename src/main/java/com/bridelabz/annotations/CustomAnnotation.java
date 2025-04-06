package com.bridelabz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// Define custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
    String priority();
    String assignedTo();
}

// TaskManager class to apply custom annotation
class TaskManager {
    @TaskInfo(priority = "High", assignedTo = "Chirag")
    void completeBackendTask() {
        System.out.println("Completing backend task...");
    }

    @TaskInfo(priority = "Low", assignedTo = "Amit")
    void updateUI() {
        System.out.println("Updating UI...");
    }
}

// Class to test a custom annotation using reflection
public class CustomAnnotation {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Load class object
            Class<TaskManager> obj = TaskManager.class;

            // Loop over methods
            Method[] methods = obj.getDeclaredMethods();
            for(Method method:methods) {
                if(method.isAnnotationPresent(TaskInfo.class)) {
                    TaskInfo info = method.getAnnotation(TaskInfo.class);
                    System.out.println("Method: " + method.getName());
                    System.out.println("Priority: " + info.priority());
                    System.out.println("Assigned To: " + info.assignedTo());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Method: completeBackendTask
// Priority: High
// Assigned To: Chirag

// Method: updateUI
// Priority: Low
// Assigned To: Amit