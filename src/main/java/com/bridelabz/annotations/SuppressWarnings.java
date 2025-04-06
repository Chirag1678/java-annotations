package com.bridelabz.annotations;

import java.util.ArrayList;

// Class to test suppress warnings
public class SuppressWarnings {
    @java.lang.SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // Create a raw ArrayList (without generics)
        ArrayList list = new ArrayList();

        // Add elements of different types
        list.add("Hello");
        list.add(42);
        list.add(3.14);

        // Display list elements
        for(Object item:list) {
            System.out.println(item);
        }
    }
}
// Sample Output ->
// Hello
// 42
// 3.14

// Suppressing unchecked warnings