package com.example.u5w3d1.services;

import com.example.u5w3d1.dto.DipendenteLoginDTO;
import com.example.u5w3d1.entities.Dipendente;
import com.example.u5w3d1.exception.UnauthorizedException;
import com.example.u5w3d1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServ {
    @Autowired
    private DipendenteSer usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(DipendenteLoginDTO payload) {
        // 1. Controllo le credenziali
        Dipendente user = usersService.findByEmail(payload.email());
        if (user.getPassword().equals(payload.password())) {
            // 2. Se tutto è OK, genero un token
            // 3. Torno il token come risposta
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali non sono OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali sbagliate!");
        }


    }
}
