package com.mazouri.ketangpai.common.security.security;

import com.mazouri.ketangpai.common.jwt.JwtUtils;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * token管理
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Component
public class TokenManager {

    private long tokenExpiration = 24*60*60*1000;
    private String tokenSignKey = "123456";

    public String createToken(String email) {
        return JwtUtils.getJwtToken(email);
    }

    public String getEmailFromToken(String token) {
        return JwtUtils.getEmailByJwtToken(token);
    }

    public void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

}
