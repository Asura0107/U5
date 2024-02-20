package com.example.u5w3d1.services;

import com.example.u5w3d1.dao.DipendenteDAO;
import com.example.u5w3d1.dto.DipendenteDTO;
import com.example.u5w3d1.dto.DipendenteLoginDTO;
import com.example.u5w3d1.entities.Dipendente;
import com.example.u5w3d1.entities.Dispositivo;
import com.example.u5w3d1.enums.DisponibileDisp;
import com.example.u5w3d1.exception.BadRequestException;
import com.example.u5w3d1.exception.UnauthorizedException;
import com.example.u5w3d1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServ {
    @Autowired
    private DipendenteSer usersService;

    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private DipendenteDAO dipendenteDAO;
    @Autowired
    private DispositivoSer dispositivoSer;

    public String authenticateUserAndGenerateToken(DipendenteLoginDTO payload) {
        // 1. Controllo le credenziali
        Dipendente user = usersService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            // 2. Se tutto è OK, genero un token
            // 3. Torno il token come risposta
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali non sono OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali sbagliate!");
        }


    }
    public Dipendente save(DipendenteDTO newuser) {
        dipendenteDAO.findByEmail(newuser.email()).ifPresent(u->{
            throw new BadRequestException("l'email "+ u.getEmail()+" è già esistente");
        });
        Dispositivo dispositivo=dispositivoSer.findById(newuser.dispositivo());
        if (dispositivo.getDisponibileDisp()== DisponibileDisp.DISMESSO||dispositivo.getDisponibileDisp()==DisponibileDisp.IN_MANUNTENZIONE){
            dispositivo=null;
        }
        return dipendenteDAO.save(
                new Dipendente(newuser.avatar(),newuser.username(),newuser.name(),newuser.surname(),newuser.email(), dispositivo, bcrypt.encode( newuser.password()))
        );
    }
}
