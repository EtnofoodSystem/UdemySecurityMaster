package com.api.course.config.security.filters;

import com.api.course.entity.User;
import com.api.course.exception.ObjectNotFoundException;
import com.api.course.service.UserService;
import com.api.course.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("ENTRO EN EL FILTRO JWT AUTHENTICATION");
        //1._OBTENER ENCABEZADO HTTP LLAMADO AUTORIZACION
        String authorizationHeader = request.getHeader("Authorization");
        if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        //2._OBTENER TOKEN JWT DESDE EL ENCABEZADO
        String jwt=authorizationHeader.split(" ")[1];
        //3._OBTENER EL SUBJECT/USERNAME DESDE EL TOKEN
        //ESTA ACCION A SU VEZ VALIDA EL FORMATO DEL TOKEN, FIRMA Y FECHA DE EXPIRACION
        String username= jwtService.extractUsername(jwt);
        //4._SETEAR OBJETO AUTHENTICATION DENTRO DE SECURITY CONTEXT HOLDER
        User user = userService.findOneByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: "+username));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null,user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        //5._EJECUTAR EL REGISTRO DEFILTROS
        filterChain.doFilter(request, response);

    }
}
