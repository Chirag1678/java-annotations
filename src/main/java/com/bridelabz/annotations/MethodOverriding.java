package com.bridelabz.annotations;

// Parent class -> Animal
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

// Subclass -> Dog
class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks...");
    }
}

// Class to test method overriding
public class MethodOverriding {
    public static void main(String[] args) {
        // Make an instance of dog class
        Dog dog = new Dog();

        // call the method of dog object
        dog.makeSound();
    }
}
// Sample Output ->
// Dog barks...