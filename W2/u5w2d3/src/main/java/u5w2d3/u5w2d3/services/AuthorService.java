package u5w2d3.u5w2d3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import u5w2d3.u5w2d3.dao.UserDAO;
import u5w2d3.u5w2d3.entities.UserPost;
import u5w2d3.u5w2d3.exception.BadRequestException;
import u5w2d3.u5w2d3.exception.NotFoundException;



@Service
public class AuthorService {

    @Autowired
    private UserDAO userDAO;

    public Page<UserPost> getUsers(int pageNumber, int size, String orderBy) {
        Pageable page = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return userDAO.findAll(page);
    }

    public UserPost findById(long id) {
        return this.userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public UserPost save(UserPost newuser) {
        userDAO.findByEmail(newuser.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });
        newuser.setAvatar("https://ui-avatars.com/api/?name" + newuser.getNome() + "+" + newuser.getCognome());
        return userDAO.save(newuser);
    }

    public UserPost findAndUpadate(long id, UserPost update) {
        UserPost found = this.findById(id);
        found.setNome(update.getNome());
        found.setCognome(update.getCognome());
        found.setEmail(update.getEmail());
        found.setDataDiNascita(update.getDataDiNascita());
        return userDAO.save(found);
    }

    public void findAndDelete(long id) {
        UserPost found = this.findById(id);
        userDAO.delete(found);
//        userDAO.deleteById(id);
    }
}
