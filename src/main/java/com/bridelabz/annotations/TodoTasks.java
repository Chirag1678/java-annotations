package com.bridelabz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// Define Todo annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

// Class to implement annotation
class PendingTasks {
    @Todo(task = "complete the given assignments", assignedTo = "Chirag", priority = "HIGH")
    public void completeAssignment() {
        System.out.println("Complete assignments of Reflection and Annotations...");
    }

    @Todo(task = "Understand topics", assignedTo = "Amit")
    public void readTheory() {
        System.out.println("Complete theory of previous topics...");
    }

    @Todo(task = "Complete file", assignedTo = "Bhanu", priority = "LOW")
    public void completeFile() {
        System.out.println("Complete assignment file till 11th...");
    }
}

// Class to print pending tasks marked with annotation using reflection
public class TodoTasks {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Get class object
            Class<PendingTasks> tasks = PendingTasks.class;

            // Get the constructor and create instance of object
            PendingTasks task = tasks.getDeclaredConstructor().newInstance();

            // Get methods of class and loop through them
            Method[] methods = tasks.getDeclaredMethods();
            for(Method method:methods) {
                if(method.isAnnotationPresent(Todo.class)) {
                    Todo annotation = method.getAnnotation(Todo.class);
                    System.out.println("Task Name: " + method.getName());
                    System.out.println("Task Description: " + annotation.task());
                    System.out.println("Assigned To: " + annotation.assignedTo());
                    System.out.println("Priority: " + annotation.priority());
                    method.invoke(task);
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Task Name: completeAssignment
// Task Description: complete the given assignments
// Assigned To: Chirag
// Priority: HIGH
// Complete assignments of Reflection and Annotations...

// Task Name: readTheory
// Task Description: Understand topics
// Assigned To: Amit
// Priority: MEDIUM
// Complete theory of previous topics...

// Task Name: completeFile
// Task Description: Complete file
// Assigned To: Bhanu
// Priority: LOW
// Complete assignment file till 11th...