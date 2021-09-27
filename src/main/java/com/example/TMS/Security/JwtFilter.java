package com.example.TMS.Security;

import com.example.TMS.ServiceImplements.UserService;
import com.example.TMS.Utils.TokenGeneration;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    TokenGeneration tokenGeneration= new TokenGeneration();

    @Autowired
    UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        try
        {
            String token = tokenGeneration.getJwtFromRequest(httpServletRequest);

            if(StringUtils.hasText(token) && tokenGeneration.validateToken(token,"secrets"))
            {
               String  ownerId = tokenGeneration.getUserNameFromJWTs(token,"secrets");

              if(Strings.isNullOrEmpty(ownerId))
              {
                  httpServletRequest.getRequestDispatcher("/error" + "invalid")
                          .forward(httpServletRequest,httpServletResponse);
              }

                UserDetails userDetails = userService.loadByUserId(ownerId);
                UsernamePasswordAuthenticationToken authentication = new
                        UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(httpServletRequest,httpServletResponse);
              }
        }
        catch (Exception e)
        {
            httpServletRequest.getRequestDispatcher("/error"+ e.getMessage())
                    .forward(httpServletRequest,httpServletResponse);
        }
    }
}
