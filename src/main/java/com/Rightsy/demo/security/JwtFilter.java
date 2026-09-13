package com.Rightsy.demo.security;

import com.Rightsy.demo.entity.User;
import com.Rightsy.demo.repository.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtFilter  extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final Authutil authutil;
    private final UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
           try{
               log.info("Incoming request : {}", request.getRequestURI());
                final String requestTokenHeader= request.getHeader("Authorization");
                if(requestTokenHeader==null||!requestTokenHeader.startsWith("Bearer")){
                    filterChain.doFilter(request,response);
                    return;
                }

                String token=requestTokenHeader.substring(7);

                String email=authutil.getEmailFromtoken(token);

                if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    User user=userRepo.findByEmail(email).orElseThrow();
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
                filterChain.doFilter(request,response);
           }
           catch (Exception ex){
               handlerExceptionResolver.resolveException(request,response,null,ex);

           }
    }
}
