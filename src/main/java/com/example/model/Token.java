package com.example.model;

import java.util.Date;

/**
 * Token实体类
 * 用于存储token相关信息
 */
public class Token {
    
    private String token;
    private String username;
    private Date issueDate;
    private Date expirationDate;
    private boolean valid;
    
    public Token() {
    }
    
    public Token(String token, String username, Date issueDate, Date expirationDate, boolean valid) {
        this.token = token;
        this.username = username;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.valid = valid;
    }
    
    // Getter和Setter方法
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Date getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    
    public Date getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    /**
     * 检查token是否过期
     * @return 是否过期
     */
    public boolean isExpired() {
        if (expirationDate == null) {
            return true;
        }
        return expirationDate.before(new Date());
    }
    
    /**
     * 获取token剩余有效时间（毫秒）
     * @return 剩余有效时间，如果已过期返回0
     */
    public long getRemainingTime() {
        if (expirationDate == null) {
            return 0;
        }
        long remaining = expirationDate.getTime() - System.currentTimeMillis();
        return Math.max(0, remaining);
    }
    
    /**
     * 获取token剩余有效时间（秒）
     * @return 剩余有效时间（秒），如果已过期返回0
     */
    public long getRemainingTimeInSeconds() {
        return getRemainingTime() / 1000;
    }
    
    @Override
    public String toString() {
        return "Token{" +
                "token='" + (token != null ? token.substring(0, Math.min(token.length(), 20)) + "..." : "null") + '\'' +
                ", username='" + username + '\'' +
                ", issueDate=" + issueDate +
                ", expirationDate=" + expirationDate +
                ", valid=" + valid +
                ", expired=" + isExpired() +
                ", remainingTime=" + getRemainingTimeInSeconds() + "秒" +
                '}';
    }
}