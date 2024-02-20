package com.example.u5w3d1.controller;

import com.example.u5w3d1.dto.DipendenteDTO;
import com.example.u5w3d1.dto.DipendenteLoginDTO;
import com.example.u5w3d1.dto.LoginResponseDTO;
import com.example.u5w3d1.entities.Dipendente;
import com.example.u5w3d1.services.AuthServ;
import com.example.u5w3d1.services.DipendenteSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServ authService;

    @Autowired
    private DipendenteSer usersService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody DipendenteLoginDTO payload) {
        return new LoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    public Dipendente saveUser(@RequestBody DipendenteDTO newUser) {

        return this.authService.save(newUser);
    }
}
