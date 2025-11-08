package com.example.controller;

import com.example.service.TokenService;
import java.util.Map;
import java.util.Scanner;

/**
 * Token控制器
 * 提供token相关的命令行交互界面
 */
public class TokenController {
    
    private TokenService tokenService;
    private Scanner scanner;
    
    public TokenController() {
        this.tokenService = new TokenService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 启动token管理程序
     */
    public void start() {
        System.out.println("=== Token管理系统 ===");
        System.out.println("1. 用户登录获取Token");
        System.out.println("2. 验证Token");
        System.out.println("3. 刷新Token");
        System.out.println("4. 注册用户");
        System.out.println("5. 退出");
        System.out.println("====================");
        
        while (true) {
            System.out.print("\n请选择操作 (1-5): ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    loginAndGetToken();
                    break;
                case "2":
                    validateToken();
                    break;
                case "3":
                    refreshToken();
                    break;
                case "4":
                    registerUser();
                    break;
                case "5":
                    System.out.println("感谢使用，再见！");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }
    
    /**
     * 用户登录获取Token
     */
    private void loginAndGetToken() {
        System.out.println("\n--- 用户登录 ---");
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();
        
        Map<String, Object> result = tokenService.loginAndGetToken(username, password);
        
        if ((Boolean) result.get("success")) {
            System.out.println("✓ 登录成功！");
            System.out.println("Token: " + result.get("token"));
            System.out.println("用户名: " + result.get("username"));
        } else {
            System.out.println("✗ 登录失败: " + result.get("message"));
        }
    }
    
    /**
     * 验证Token
     */
    private void validateToken() {
        System.out.println("\n--- 验证Token ---");
        System.out.print("请输入Token: ");
        String token = scanner.nextLine();
        
        Map<String, Object> result = tokenService.validateToken(token);
        
        System.out.println("验证结果:");
        System.out.println("有效: " + result.get("valid"));
        System.out.println("消息: " + result.get("message"));
        
        if (result.containsKey("username")) {
            System.out.println("用户名: " + result.get("username"));
        }
        if (result.containsKey("expired")) {
            System.out.println("已过期: " + result.get("expired"));
        }
    }
    
    /**
     * 刷新Token
     */
    private void refreshToken() {
        System.out.println("\n--- 刷新Token ---");
        System.out.print("请输入旧的Token: ");
        String oldToken = scanner.nextLine();
        
        Map<String, Object> result = tokenService.refreshToken(oldToken);
        
        if ((Boolean) result.get("success")) {
            System.out.println("✓ Token刷新成功！");
            System.out.println("新的Token: " + result.get("token"));
        } else {
            System.out.println("✗ Token刷新失败: " + result.get("message"));
        }
    }
    
    /**
     * 注册用户
     */
    private void registerUser() {
        System.out.println("\n--- 注册用户 ---");
        System.out.print("请输入新用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();
        
        boolean success = tokenService.registerUser(username, password);
        
        if (success) {
            System.out.println("✓ 用户注册成功！");
        } else {
            System.out.println("✗ 用户注册失败，用户名可能已存在或输入无效！");
        }
    }
    
    /**
     * 主方法，启动应用程序
     */
    public static void main(String[] args) {
        TokenController controller = new TokenController();
        controller.start();
    }
}