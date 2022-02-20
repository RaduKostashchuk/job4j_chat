package ru.job4j;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

import static ru.job4j.filter.JWTAuthenticationFilter.HEADER_STRING;
import static ru.job4j.filter.JWTAuthenticationFilter.TOKEN_PREFIX;
import static ru.job4j.filter.JWTAuthenticationFilter.SECRET;

public class Util {

    public static String getUserFromToken(HttpServletRequest request) {
        String result = null;
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            token = token.replace(TOKEN_PREFIX, "");
            result = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
        }
        return result;
    }
}
