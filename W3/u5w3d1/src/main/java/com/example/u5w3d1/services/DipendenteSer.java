package com.example.u5w3d1.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.u5w3d1.dao.DipendenteDAO;
import com.example.u5w3d1.dto.DipendenteDTO;
import com.example.u5w3d1.entities.Dipendente;
import com.example.u5w3d1.entities.Dispositivo;
import com.example.u5w3d1.enums.DisponibileDisp;
import com.example.u5w3d1.exception.BadRequestException;
import com.example.u5w3d1.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
;

import java.io.IOException;

@Service
public class DipendenteSer {
    @Autowired
    private DipendenteDAO dipendenteDAO;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private DispositivoSer dispositivoSer;


    public Page<Dipendente> getDipendenti(int pageNumber, int size, String orderBy) {
        Pageable page = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return dipendenteDAO.findAll(page);
    }

    public Dipendente findById(long id) {
        return dipendenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Dipendente findByEmail(String email) {
        return dipendenteDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " non trovata"));
    }
    public Dipendente findAndUpdate (long id, Dipendente update){
        Dipendente dipendente=this.findById(id);
        dipendente.setAvatar(update.getAvatar());
        dipendente.setUsername(update.getUsername());
        dipendente.setName(update.getName());
        dipendente.setSurname(update.getSurname());
        dipendente.setEmail(update.getEmail());
        return dipendenteDAO.save(dipendente);
    }

    public void findAndDelete(long id){
        Dipendente found=this.findById(id);
        dipendenteDAO.delete(found);
    }

    public String findAndPostAvatar(long id, MultipartFile image)throws IOException {
        Dipendente dipendente=this.findById(id);
        String url = (String) cloudinary.uploader().upload(image.getBytes(),
                ObjectUtils.emptyMap()).get("url");
        dipendente.setAvatar(url);
        dipendenteDAO.save(dipendente);
        return url;
    }

    public void findAndPostDisp(long id, long dispid){
        Dipendente dipendente=this.findById(id);
        Dispositivo dispositivo=dispositivoSer.findById(dispid);
        dipendente.setDispositivo(dispositivo);
        dipendenteDAO.save(dipendente);
    }
}

