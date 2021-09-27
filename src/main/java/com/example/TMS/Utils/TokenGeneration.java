package com.example.TMS.Utils;

import com.example.TMS.EntityORModel.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Date;


public class TokenGeneration {

  //  private static String secret = "This_is_secret";
   // private static long expiryDuration = 60 * 60;

    public static String generateToken(String subject,String userName, String userPassword)
    {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setSubject(subject)
                .claim("UserName",userName)
                .claim("UserPassword",userPassword)
                .signWith(SignatureAlgorithm.HS256,"secrets")
                .setIssuedAt(now);
        return builder.compact();
    }

    /*public String tokencreation(User user)
    {
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration *1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        //claims
        Claims claims = Jwts.claims()
                .setIssuer(user.getOwnerId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        //Optional claims
        claims.put("UserName",user.getUserName());
        claims.put("UserType",user.getUserType());
        claims.put("City",user.getCity());
        claims.put("PhoneNumber",user.getPhoneNumber());

        //generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }*/


    /*public void verify(String authorization) throws Exception
    {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);
        }
        catch (Exception e)
        {
            throw new Exception();
        }

    }*/


    public static String getJwtFromRequest(HttpServletRequest httpServletRequest)
    {
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer"))
        {
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }


    public boolean validateToken(String authToken, String signinkey) {
        try {
            Jwts.parser().setSigningKey(signinkey).parseClaimsJws(authToken);
            return true;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    public String getUserNameFromJWTs(String token, String signinkey) {
         Claims claims = Jwts.parser().setSigningKey(signinkey).parseClaimsJws(token).getBody();
          return String.valueOf(claims.get("userName"));
    }
}
