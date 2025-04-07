package com.bridelabz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// Define custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
}

// Class to get execution time using annotation
class CalculateTime {
    // method whose execution time to be calculated
    @LogExecutionTime()
    public void longTask() {
        for(int i=0;i<1000000;i++) {
            int x = i*i;
        }
    }

    @LogExecutionTime
    public void smallTask() {
        for(int i=0;i<1000;i++) {
            int x = i*i;
        }
    }
}

// Class to measure execution time of methods using annotation
public class MeasureExecutionTime {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Get class object
            CalculateTime obj = new CalculateTime();
            Class<?> findTime = obj.getClass();

            // Get class methods
            Method[] methods = findTime.getDeclaredMethods();

            // loop through methods
            for(Method method:methods) {
                if(method.isAnnotationPresent(LogExecutionTime.class)) {
                    long startTime = System.nanoTime();

                    // invoke method
                    method.invoke(obj);

                    long endTime = System.nanoTime();
                    long duration = (endTime-startTime);

                    System.out.println("Executed method: " + method.getName());
                    System.out.println("Execution time: " + duration/1e6 + " ms.");
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Executed method: longTask
// Execution time: 4.775875 ms.

// Executed method: smallTask
// Execution time: 0.153083 ms.