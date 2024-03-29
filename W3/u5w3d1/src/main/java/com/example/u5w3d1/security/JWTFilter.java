package com.example.u5w3d1.security;

import com.example.u5w3d1.entities.Dipendente;
import com.example.u5w3d1.exception.UnauthorizedException;
import com.example.u5w3d1.services.DipendenteSer;
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
public class JWTFilter  extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private DipendenteSer usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Il codice di questo metodo verrà eseguito ad ogni richiesta che richieda di essere autenticati
        // Cose da fare:

        // 1. Verifichiamo se la richiesta effettivamente contiene un Authorization Header, se non c'è --> 401
        String authHeader = request.getHeader("Authorization"); // Bearer eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MDgzNDAxMDgsImV4cCI6MTcwODQyNjUwOCwic3ViIjoiYzk2NGI0MGMtYjM5Yi00ODc2LWEwZGItYzQwOGI3OWQ5YTQ1In0.Kt8bZ4KdseMY9ZcKRyOpr3KDZieeLY78uE4xt4pkho4qJMn2wKVlmEQ7oENW1ptN
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Per favore metti il token nell'header");

        // 2. Se c'è estraiamo il token dall'header, conta la stringa dal settimo carattere( cioè dopo bearer ) in poi
        String accessToken = authHeader.substring(7);

        System.out.println("ACCESS TOKEN " + accessToken);

        // 3. Verifichiamo se il token è stato manipolato (verifica signature) e se non è scaduto (verifica Expiration Date)
        jwtTools.verifyToken(accessToken);

        // 4. Se è tutto OK proseguiamo con la catena fino ad arrivare all'endpoint

        // 4.1 Cerco l'utente nel DB (l'id sta nel token..)
        String id = jwtTools.extractIdFromToken(accessToken);
        Dipendente user = usersService.findById(Long.parseLong(id));

        // 4.2 Devo informare Spring Security che l'utente è autenticato (se non faccio questo step riceverò 403 come risposta)
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        //il primo parametro rappresenta lo user che si sta autenticando, il secondo parametro rappresenta le credenziali dell'utente(dato che usiamo username e passwor come
        //credenziali, e per motivi di sicurezza la password non deve apparire allora è null)
        // OBBLIGATORIO il terzo parametro con la lista dei ruoli dell'utente se si vuole "abilitare" il meccanismo di autorizzazione
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4.3 Proseguo ora col prossimo elemento della catena
        filterChain.doFilter(request, response); // va al prossimo elemento della catena

        // 5. Se non è OK --> 401
    }

    // Disabilito il filtro per determinate richieste tipo Login o Register (non devono richiedere token)
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Uso questo metodo per specificare in che situazioni il filtro NON DEVE FILTRARE (non deve chiedere il token)
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
        // Se l'URL della richiesta corrente corrisponde a /auth/qualsiasicosa, allora il filtro non entra in azione
    }
}
