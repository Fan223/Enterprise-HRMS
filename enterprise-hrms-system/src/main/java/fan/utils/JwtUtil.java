package fan.utils;

import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/5 18:25
 * @Version 1.0
 */
@Data
@Component
public class JwtUtil {

    @Value("${fan.jwt.expire}")
    private String expire;
    @Value("${fan.jwt.header}")
    private String header;
    private final static RSA rsa = new RSA();

    // 生成 JWT
    public String generateJwt(String username) {

        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) rsa.getPrivateKey();

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expire))) // 设置过期时间
                .signWith(SignatureAlgorithm.RS256, rsaPrivateKey)
                .compact();
        System.out.println(jwt + "  生成的jwt");
        return jwt;
    }

    // 解析 JWT
    public Jws<Claims> parseJwt(String jwt) {

        RSAPublicKey rsaPublicKey = (RSAPublicKey) rsa.getPublicKey();
        try {
            return Jwts.parser().setSigningKey(rsaPublicKey).parseClaimsJws(jwt);
        } catch (Exception e) {
            return null;
        }
    }
}
