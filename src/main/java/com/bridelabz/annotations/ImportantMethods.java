package com.bridelabz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// Define annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH" ;
}

// Class to apply annotation
class CompleteTask {
    @ImportantMethod(level = "CRITICAL")
    public void completeJUnit() {
        System.out.println("Complete JUnit assignment asap...");
    }

    @ImportantMethod // Uses default level = HIGH
    public void completeReflection() {
        System.out.println("Complete assignment of Reflection...");
    }

    public void learnSpringBoot() {
        System.out.println("Learn about springboot framework...");
    }
}

// Class to fetch methods marked with ImportantMethod annotation using reflection
public class ImportantMethods {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Get class object
            Class<CompleteTask> tasks = CompleteTask.class;

            // Loop through methods
            Method[] taskMethods = tasks.getDeclaredMethods();
            for(Method method:taskMethods) {
                if(method.isAnnotationPresent(ImportantMethod.class)) {
                    ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                    System.out.println("Important Method: " + method.getName());
                    System.out.println("Importance Level: " + annotation.level());
                    // Invoke method
                    method.invoke(tasks.getDeclaredConstructor().newInstance());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Important Method: completeJUnit
// Importance Level: CRITICAL
// Complete JUnit assignment asap...

// Important Method: completeReflection
// Importance Level: HIGH
// Complete assignment of Reflection...