package com.bridelabz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// Define custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {
}

// Class to implement annotation
class FibonacciUsingCache {
    // Attribute
    private Map<String, Object> cache = new HashMap<>();

    @CacheResult
    public int fibonacci(int n) {
        if(n<=1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    // method to store and invoke method with cache
    public Object invokeWithCache(String methodName, Object ...args) {
        try {
            // Generate cache key based on method and args
            StringBuilder sb = new StringBuilder(methodName);
            for(Object arg:args) {
                sb.append("-").append(arg.toString());
            }
            String key = sb.toString();

            // Return cache result if exists
            if(cache.containsKey(key)) {
                System.out.println("Returning cached result for " + key);
                return cache.get(key);
            }

            // Get method reference using reflection
            Method method = null;
            for(Method m:this.getClass().getDeclaredMethods()) {
                if(m.getName().equals(methodName) && m.isAnnotationPresent(CacheResult.class)) {
                    method = m;
                    break;
                }
            }

            if(method == null) {
                throw new IllegalArgumentException("Method not found or not annotated with @CacheResult");
            }

            // Invoke and store result in cache
            Object result = method.invoke(this, args);
            cache.put(key, result);
            System.out.println("Computed and cached result for: " + key);
            return result;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return null;
    }
}

// Class to get method results from cache instead of recalculating
public class CacheMethodResults {
    public static void main(String[] args) {
        // Create Object of class
        FibonacciUsingCache fibonacci = new FibonacciUsingCache();

        // Compute results
        System.out.println("Fibonacci(20): " + fibonacci.invokeWithCache("fibonacci", 20));
        System.out.println("Fibonacci(20): " + fibonacci.invokeWithCache("fibonacci", 20));
        System.out.println("Fibonacci(10): " + fibonacci.invokeWithCache("fibonacci", 10));
    }
}
// Sample Output ->
// Computed and cached result for: fibonacci-20
// Fibonacci(20): 6765
// Returning cached result for fibonacci-20
// Fibonacci(20): 6765
// Computed and cached result for: fibonacci-10
// Fibonacci(10): 55