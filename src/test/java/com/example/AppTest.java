package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for App
 */
public class AppTest {
    
    @Test
    public void testGreet() {
        App app = new App();
        String result = app.greet("Maven");
        assertEquals("Hello, Maven!", result);
    }
    
    @Test
    public void testGreetWithDifferentName() {
        App app = new App();
        String result = app.greet("World");
        assertEquals("Hello, World!", result);
    }
}