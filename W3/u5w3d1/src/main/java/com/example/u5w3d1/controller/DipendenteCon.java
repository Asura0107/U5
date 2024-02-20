package com.example.u5w3d1.controller;

import com.example.u5w3d1.dto.DipendenteDTO;
import com.example.u5w3d1.entities.Dipendente;
import com.example.u5w3d1.exception.BadRequestException;
import com.example.u5w3d1.services.AuthServ;
import com.example.u5w3d1.services.DipendenteSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteCon {
    @Autowired
    private DipendenteSer dipendenteSer;
    @Autowired
    private AuthServ authServ;

    @GetMapping
    public Page<Dipendente> getallDipendenti(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size,
                                             @RequestParam(defaultValue = "id") String orderBy) {
        return dipendenteSer.getDipendenti(page, size, orderBy);
    }
    @GetMapping("/me")
    public Dipendente getProfile(@AuthenticationPrincipal Dipendente currentAuthenticatedUser) {
        // @AuthenticationPrincipal mi consente di accedere all'utente attualmente autenticato
        // Posso fare ciò perché precedentemente abbiamo estratto l'id utente (nel jwtfilter) dal token e abbiamo cercato
        // nel db l'utente, aggiungendolo poi al Security Context
        return currentAuthenticatedUser;
    }
    @PutMapping("/me")
    public Dipendente getMeAndUpdate(@AuthenticationPrincipal Dipendente currentAuthenticatedUser, @RequestBody Dipendente updatedUser) {
        return this.dipendenteSer.findAndUpdate(currentAuthenticatedUser.getId(), updatedUser);
    }
    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getMeAndDelete(@AuthenticationPrincipal Dipendente currentAuthenticatedUser) {
        this.dipendenteSer.findAndDelete(currentAuthenticatedUser.getId());
    }
    @PostMapping("/me/avatar")
    public String uploadCoverMe(@AuthenticationPrincipal Dipendente currentAuthenticatedUser, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.dipendenteSer.findAndPostAvatar(currentAuthenticatedUser.getId(), image);
    }

    @GetMapping("/{id}")
    public Dipendente getSingleDipendente(@PathVariable long id) {
        return dipendenteSer.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente seveDipen(@RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return authServ.save(dipendenteDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Dipendente findAndUpdate(@PathVariable long id, @RequestBody @Validated Dipendente dipendente, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return dipendenteSer.findAndUpdate(id, dipendente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id) {
        this.dipendenteSer.findAndDelete(id);
    }

    @PostMapping("/{id}/avatar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadCover(@PathVariable long id, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.dipendenteSer.findAndPostAvatar(id,image);
    }

    @PostMapping("/{id}/newdisp")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void uploadDispositivo(@PathVariable long id, @RequestParam("disp") long dispid) throws IOException {
        this.dipendenteSer.findAndPostDisp(id,dispid);
    }

}
