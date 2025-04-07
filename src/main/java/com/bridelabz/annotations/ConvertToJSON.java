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
@interface JsonField {
    String name();
}

// Define class for annotation
class UserDetails {
    // Attributes
    @JsonField(name = "user_name") private String username;
    @JsonField(name = "full_name") private String fullName;
    @JsonField(name = "age") private int age;
    @JsonField(name = "gender") private char gender;

    // Constructor
    UserDetails(String username, String fullName, int age, char gender) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

}

// Class to convert a class object to json representation using annotation
public class ConvertToJSON {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try(Scanner input = new Scanner(System.in)) {
            // Take user inputs
            System.out.print("Enter username: ");
            String username = input.nextLine();

            System.out.print("Enter full name: ");
            String fullName = input.nextLine();

            System.out.print("Enter age: ");
            int age = input.nextInt();

            System.out.print("Enter gender: ");
            char gender = input.next().charAt(0);

            // Load class object
            UserDetails user = new UserDetails(username, fullName, age, gender);
            Class<?> userClass = user.getClass();

            // Check fields with annotation and print in jSON format
            Field[] userFields = userClass.getDeclaredFields();

            System.out.println("JSON representation of User Object ->");
            System.out.println("{");
            for(Field field:userFields) {
                if(field.isAnnotationPresent(JsonField.class)) {
                    JsonField annotation = field.getAnnotation(JsonField.class);
                    field.setAccessible(true);
                    System.out.println(" \"" + annotation.name() + "\" : " + field.get(user) + ",");
                }
            }
            System.out.println("}");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Enter username: chirag_1708
// Enter full name: Chirag Garg
// Enter age: 21
// Enter gender: M

// JSON representation of User Object ->
// {
//  "username" : chirag_1708,
//  "full_name" : Chirag Garg, 
//  "age" : 21,
//  "gender" : M,
// }