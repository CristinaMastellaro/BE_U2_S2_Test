package cristinamastellaro.BE_U2_S2_Test.security;

import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import cristinamastellaro.BE_U2_S2_Test.exceptions.UnauthorizedException;
import cristinamastellaro.BE_U2_S2_Test.services.EmployeeService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private EmployeeService eServ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Che ci sia l'auth header e che sia scritto bene
        String loginHeader = request.getHeader("Authorization");
        if (loginHeader == null || !loginHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Insert the token in the correct format in the Authorization header!");
        }

        // 2. Estraiamo il token
        String token = loginHeader.replace("Bearer ", "");

        // 3. Che il token sia valido
        jwtTools.verifyToken(token);

        UUID idUser = jwtTools.extractIdFromToken(token);
        Employee found = eServ.findEmployeeById(idUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(found, null, found.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 4. Passiamo alla fase successiva (token o controller che sia)
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/log/**", request.getServletPath());
    }
}
