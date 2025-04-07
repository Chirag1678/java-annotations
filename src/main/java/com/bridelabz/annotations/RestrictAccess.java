package com.bridelabz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Scanner;

// Define custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RoleAllowed {
    String assignedTo();
}

// class to use annotation
@RoleAllowed(assignedTo = "ADMIN")
class Admin {
    public void assignTasks() {
        System.out.println("Tasks assigned to team...");
    }

    public void logHours() {
        System.out.println("Working hours of employees logged...");
    }

    public void sendSalary() {
        System.out.println("Salaries sent to employees...");
    }
}

// Class to use custom annotation to restrict access to different roles
public class RestrictAccess {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try(Scanner input = new Scanner(System.in)) {
            // Take role from user
            System.out.print("Enre your role: ");
            String role = input.next();

            // Load class object
            Class<?> cls = Admin.class;

            // check role with annotation
            if(cls.isAnnotationPresent(RoleAllowed.class)) {
                RoleAllowed annotation = cls.getAnnotation(RoleAllowed.class);
                if(!annotation.assignedTo().equalsIgnoreCase(role)) {
                    throw new IllegalArgumentException("Access Denied!");
                }
            }

            // Get class methods and print them
            Method[] methods = cls.getDeclaredMethods();
            for(Method method:methods) {
                method.invoke((Admin)cls.getDeclaredConstructor().newInstance());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Enre your role: ADMIN
// Tasks assigned to team...
// Working hours of employees logged...
// Salaries sent to employees...

// Enre your role: USER
// Error: Access Denied!