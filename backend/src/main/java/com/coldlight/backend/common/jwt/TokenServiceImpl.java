package com.coldlight.backend.common.jwt;

import com.coldlight.backend.common.enums.UserType;
import com.coldlight.backend.common.exception.ForbiddenException;
import com.coldlight.backend.common.exception.UnauthorizedException;
import com.coldlight.backend.common.request.ClientType;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/11 16:56
 * @description 令牌服务实现类
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService{


    @Value("${token.secret}")
    private String secret;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final long ACCESS_TOKEN_EXPIRE_SECONDS = 2 * 60 * 60; // 2小时
    private static final long REFRESH_TOKEN_EXPIRE_SECONDS = 7 * 24 * 60 * 60; // 7天


    @Override
    public TokenResult generateToken(Long userId, UserType userType, ClientType clientType) {
        String accessToken = createAccessToken(userId, userType, clientType);
        String refreshTokenId = UUID.randomUUID().toString().replace("-", "");

        String redisKey = buildRefreshKey(userId, userType, clientType);
        redisTemplate.opsForValue().set(redisKey, refreshTokenId, REFRESH_TOKEN_EXPIRE_SECONDS, TimeUnit.SECONDS);

        return new TokenResult(accessToken, refreshTokenId, ACCESS_TOKEN_EXPIRE_SECONDS);
    }

    private String createAccessToken(Long userId, UserType userType, ClientType clientType) {
        Instant now = Instant.now();

        Map<String, Object> claims = new HashMap<>(2);
        claims.put("userType", userType);
        claims.put("clientType", clientType);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(now.plusSeconds(ACCESS_TOKEN_EXPIRE_SECONDS)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("Token 已过期");
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            throw new UnauthorizedException("无效的 Token");
        } catch (Exception e) {
            throw new UnauthorizedException("Token 解析失败");
        }
    }

    private String buildRefreshKey(Long userId, UserType userType, ClientType clientType) {
        return String.format("refresh_token:%d:%s:%s", userId, userType, clientType);
    }

    @Override
    public TokenResult refreshToken(String refreshTokenId, UserType userType, ClientType clientType) {
       /* Long userId = getUserIdFromRefreshToken(refreshTokenId, clientType, deviceId);
        if (userId == null) {
            throw new UnauthorizedException("RefreshToken 无效");
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));

        if (!user.isEnabled() || user.isDeleted() || user.getStatus() == UserStatus.BANNED) {
            throw new ForbiddenException("用户已被禁用或冻结");
        }

        return generateToken(userId, clientType, deviceId);*/
        return null;
    }

    @Override
    public void invalidateToken(String refreshTokenId) {
        // 可实现 refreshToken 失效逻辑（踢人）
    }
}
