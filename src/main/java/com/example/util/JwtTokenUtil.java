package com.example.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token工具类
 * 用于生成和验证JWT token
 */
public class JwtTokenUtil {
    
    // 密钥 - 在实际应用中应该从配置文件或环境变量获取
    private static final String SECRET_KEY = "mySecretKey123456789012345678901234567890";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    
    // token过期时间（毫秒）- 24小时
    private static final long EXPIRATION_TIME = 86400000;
    
    /**
     * 生成JWT token
     * @param username 用户名
     * @return JWT token字符串
     */
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("created", new Date());
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }
    
    /**
     * 从token中获取用户名
     * @param token JWT token
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }
    
    /**
     * 验证token是否有效
     * @param token JWT token
     * @return 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * 检查token是否已过期
     * @param token JWT token
     * @return 是否过期
     */
    public static boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    /**
     * 从token中获取过期时间
     * @param token JWT token
     * @return 过期时间
     */
    public static Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }
    
    /**
     * 从token中获取claims
     * @param token JWT token
     * @return claims对象
     */
    private static Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    /**
     * 刷新token
     * @param token 旧的token
     * @return 新的token
     */
    public static String refreshToken(String token) {
        String username = getUsernameFromToken(token);
        return generateToken(username);
    }
}