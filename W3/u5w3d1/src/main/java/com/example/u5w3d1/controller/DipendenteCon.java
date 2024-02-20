package com.example.u5w3d1.controller;

import com.example.u5w3d1.dto.DipendenteDTO;
import com.example.u5w3d1.entities.Dipendente;
import com.example.u5w3d1.exception.BadRequestException;
import com.example.u5w3d1.services.DipendenteSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public Page<Dipendente> getallDipendenti(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size,
                                             @RequestParam(defaultValue = "id") String orderBy) {
        return dipendenteSer.getDipendenti(page, size, orderBy);
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
        return dipendenteSer.save(dipendenteDTO);
    }

    @PutMapping("/{id}")
    public Dipendente findAndUpdate(@PathVariable long id, @RequestBody @Validated Dipendente dipendente, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return dipendenteSer.findAndUpdate(id, dipendente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id) {
        this.dipendenteSer.findAndDelete(id);
    }

    @PostMapping("/{id}/avatar")
    public String uploadCover(@PathVariable long id, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.dipendenteSer.findAndPostAvatar(id,image);
    }

    @PostMapping("/{id}/newdisp")
    public void uploadDispositivo(@PathVariable long id, @RequestParam("disp") long dispid) throws IOException {
        this.dipendenteSer.findAndPostDisp(id,dispid);
    }

}
