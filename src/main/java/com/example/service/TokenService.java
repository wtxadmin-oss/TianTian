package com.example.service;

import com.example.util.JwtTokenUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * Token服务类
 * 处理token相关的业务逻辑
 */
public class TokenService {
    
    // 模拟用户数据库
    private static final Map<String, String> USER_DB = new HashMap<>();
    
    static {
        // 初始化一些测试用户
        USER_DB.put("admin", "admin123");
        USER_DB.put("user1", "password1");
        USER_DB.put("user2", "password2");
    }
    
    /**
     * 用户登录并获取token
     * @param username 用户名
     * @param password 密码
     * @return 包含token的结果Map
     */
    public Map<String, Object> loginAndGetToken(String username, String password) {
        Map<String, Object> result = new HashMap<>();
        
        // 验证用户名和密码
        if (validateUser(username, password)) {
            // 生成token
            String token = JwtTokenUtil.generateToken(username);
            
            result.put("success", true);
            result.put("token", token);
            result.put("message", "登录成功");
            result.put("username", username);
            return result;
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
            return result;
        }
    }
    
    /**
     * 验证token有效性
     * @param token JWT token
     * @return 验证结果Map
     */
    public Map<String, Object> validateToken(String token) {
        Map<String, Object> result = new HashMap<>();
        
        if (token == null || token.trim().isEmpty()) {
            result.put("valid", false);
            result.put("message", "token不能为空");
            return result;
        }
        
        try {
            boolean isValid = JwtTokenUtil.validateToken(token);
            if (isValid) {
                String username = JwtTokenUtil.getUsernameFromToken(token);
                boolean isExpired = JwtTokenUtil.isTokenExpired(token);
                
                result.put("valid", !isExpired);
                result.put("username", username);
                result.put("expired", isExpired);
                
                if (isExpired) {
                    result.put("message", "token已过期");
                } else {
                    result.put("message", "token有效");
                }
            } else {
                result.put("valid", false);
                result.put("message", "token无效");
            }
        } catch (Exception e) {
            result.put("valid", false);
            result.put("message", "token验证失败: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 刷新token
     * @param oldToken 旧的token
     * @return 新的token
     */
    public Map<String, Object> refreshToken(String oldToken) {
        Map<String, Object> result = new HashMap<>();
        
        if (oldToken == null || oldToken.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "旧token不能为空");
            return result;
        }
        
        try {
            // 验证旧token是否有效
            Map<String, Object> validationResult = validateToken(oldToken);
            if (!(Boolean) validationResult.get("valid")) {
                result.put("success", false);
                result.put("message", "旧token无效或已过期");
                return result;
            }
            
            // 生成新token
            String newToken = JwtTokenUtil.refreshToken(oldToken);
            
            result.put("success", true);
            result.put("token", newToken);
            result.put("message", "token刷新成功");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "token刷新失败: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 从token中获取用户名
     * @param token JWT token
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return JwtTokenUtil.getUsernameFromToken(token);
    }
    
    /**
     * 验证用户身份
     * @param username 用户名
     * @param password 密码
     * @return 是否验证通过
     */
    private boolean validateUser(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        
        String storedPassword = USER_DB.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
    
    /**
     * 注册用户（测试用）
     * @param username 用户名
     * @param password 密码
     * @return 是否注册成功
     */
    public boolean registerUser(String username, String password) {
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }
        
        if (USER_DB.containsKey(username)) {
            return false; // 用户已存在
        }
        
        USER_DB.put(username, password);
        return true;
    }
}