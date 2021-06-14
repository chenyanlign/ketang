package com.mazouri.ketangpai.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author helen
 * @since 2019/10/16
 */
public class JwtUtils {
    //token过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //根据密钥做一个字符加密(随便生成的密钥)
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    //生成token字符串
    public static String getJwtToken(String id, String nickname){

        return Jwts.builder()
                //设置jwt的头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //设置过期时间
                .setSubject("ketangpai")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //设置token的主体部分
                .claim("id", id)
                .claim("nickname", nickname)
                //设置签名哈希(就是防伪标志)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) {
                return false;
            }
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static String getUserIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        System.out.println("*********JwtUtils*********claims"+claims);
        String id = (String) claims.get("id");
        System.out.println("******************id"+id);
        return id;
    }

//    /**
//     * 根据token获取用户姓名
//     * @param request
//     * @return
//     */
//    public static String getUserNameByToken(HttpServletRequest request) {
//        String jwtToken = request.getHeader("Authorization");
//        if(StringUtils.isEmpty(jwtToken)) {
//            return "";
//        }
//        String token = jwtToken.replace("bearer ", "");
//
//        DecodedJWT decode = JWT.decode(token);
//        return decode.getClaim("user_name").asString();
//    }


}
