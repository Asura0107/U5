package u5w2d3.u5w2d3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import u5w2d3.u5w2d3.dao.PostDAO;
import u5w2d3.u5w2d3.entities.BlogPost;
import u5w2d3.u5w2d3.entities.PostPayload;
import u5w2d3.u5w2d3.entities.UserPost;
import u5w2d3.u5w2d3.exception.BadRequestException;
import u5w2d3.u5w2d3.exception.NotFoundException;



@Service
public class PostService {
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private AuthorService authorService;

    public Page<BlogPost> getPosts(int pageNumber, int size, String orderBy) {
        Pageable page = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return postDAO.findAll(page);
    }

    public BlogPost findById(long id) {
        return this.postDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost save(BlogPost newpost) {
        postDAO.findByTitolo(newpost.getTitolo()).ifPresent(post -> {
            throw new BadRequestException("Il titolo " + newpost.getTitolo() + " è già in uso!");
        });
        return postDAO.save(newpost);
    }

    public BlogPost findAndUpadate(long id, BlogPost update) {
        BlogPost found = this.findById(id);
        found.setCategoria(update.getCategoria());
        found.setTitolo(update.getTitolo());
        found.setCover(update.getCover());
        found.setContenuto(update.getContenuto());
        found.setTempoDiLettura(update.getTempoDiLettura());
        return postDAO.save(found);
    }

    public void findAndDelete(long id) {
        BlogPost found = this.findById(id);
        postDAO.delete(found);
//        postDAO.deleteById(id);
    }

    public PostPayload userPaylod(BlogPost user){
        return  new PostPayload(user.getUser().getId());
    }
}
