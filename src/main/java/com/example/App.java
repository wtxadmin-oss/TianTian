package com.example;

import com.example.controller.TokenController;
import java.util.Scanner;

/**
 * Main application class
 */
public class App {
    
    public static void main(String[] args) {
        System.out.println("欢迎使用Token管理系统！");
        System.out.println("==================");
        
        // 提供选择：使用原始功能或Token管理功能
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("请选择要使用的功能：");
        System.out.println("1. 原始问候功能");
        System.out.println("2. Token管理功能");
        System.out.print("请选择 (1-2): ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                // 原始功能
                System.out.println("Hello Maven!");
                System.out.print("请输入您的名字进行问候测试: ");
                String name = scanner.nextLine();
                App app = new App();
                System.out.println(app.greet(name));
                break;
            case "2":
                // Token管理功能
                System.out.println("\n启动Token管理系统...");
                TokenController tokenController = new TokenController();
                tokenController.start();
                break;
            default:
                System.out.println("无效的选择，程序退出。");
        }
        
        scanner.close();
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