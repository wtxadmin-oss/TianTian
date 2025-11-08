package com.example;

/**
 * Main application class
 */
public class App {
    
    public static void main(String[] args) {
        System.out.println("Hello Maven!");
    }
    
    /**
     * Sample method to demonstrate testing
     * @param name the name to greet
     * @return greeting message
     */
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}