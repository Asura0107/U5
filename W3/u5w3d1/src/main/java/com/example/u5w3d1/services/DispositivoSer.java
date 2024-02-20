package com.example.u5w3d1.services;

import com.example.u5w3d1.dao.DispositivoDAO;
import com.example.u5w3d1.dto.DispositivoDTO;
import com.example.u5w3d1.entities.Dispositivo;
import com.example.u5w3d1.enums.DisponibileDisp;
import com.example.u5w3d1.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DispositivoSer {
    @Autowired
    private DispositivoDAO dispositivoDAO;


    public Dispositivo findById(long id) {
        return this.dispositivoDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public Page<Dispositivo> getDispositivi(int pageNumber, int size, String orderBy) {
        Pageable page = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return dispositivoDAO.findAll(page);
    }

    public Dispositivo save(DispositivoDTO newdisp) {
        DisponibileDisp disponibilita = DisponibileDisp.valueOf(newdisp.disponibile());
        Dispositivo dispositivo= new Dispositivo(newdisp.tipo(), disponibilita);
        return dispositivoDAO.save(dispositivo);
    }
    public Dispositivo findAndUpdate (long id, Dispositivo update){
        Dispositivo dispositivo=this.findById(id);
        dispositivo.setTipo(update.getTipo());
        dispositivo.setDisponibileDisp(update.getDisponibileDisp());
        return dispositivoDAO.save(dispositivo);
    }

    public void findAndDelete(long id){
        Dispositivo found=this.findById(id);
        dispositivoDAO.delete(found);
    }

}
