package com.bridelabz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Scanner;

// Define custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// Class tp use annotation
class User {
    // Attributes
    @MaxLength(value = 8)
    public String username;
    public int age;
    public char gender;

    // Constructor
    User(String username, int age, char gender) {
        this.username = username;
        this.age = age;
        this.gender = gender;
    }

    // method to display user details
    public void displayDetails() {
        System.out.println("User registered successfully!!");
        System.out.println("User details ->");
        System.out.println("Username: " + username);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }
}

// Use custom annotation to restrict username length
public class ValidateUser {
    public static void main(String[] args) {
        // Use try-catch to handle exception
        try(Scanner input = new Scanner(System.in)) {
            // Take user input
            System.out.print("Enter username: ");
            String username = input.next();

            System.out.print("Enter age: ");
            int age = input.nextInt();

            System.out.print("Enter gender: ");
            char gender = input.next().charAt(0);

            // Load class object
            User user = new User(username, age, gender);
            Class<?> cls =user.getClass();

            // loop through fields to check length restriction
            Field[] userFields = cls.getDeclaredFields();
            for(Field userField:userFields) {
                if(userField.isAnnotationPresent(MaxLength.class)) {
                    MaxLength annotation = userField.getAnnotation(MaxLength.class);
                    if(userField.get(user).toString().length()>annotation.value()) {
                        throw new IllegalArgumentException("Username must be less than 8 characters...");
                    }
                }
            }
            user.displayDetails();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Enter username: Chirag123
// Enter age: 21
// Enter gender: M
// IllegalArgumentException: Username must be less than 8 characters...

// Enter username: Chirag
// Enter age: 21
// Enter gender: M
// User registered successfully!!
// User details ->
// Username: Chirag
// Age: 21
// Gender: M