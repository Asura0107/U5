package u5w2d3.u5w2d3.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import u5w2d3.u5w2d3.dao.PostDAO;
import u5w2d3.u5w2d3.entities.BlogPost;
import u5w2d3.u5w2d3.entities.PostPayload;
import u5w2d3.u5w2d3.entities.UserPost;
import u5w2d3.u5w2d3.exception.NotFoundException;

import java.io.IOException;


@Service
public class PostService {
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private Cloudinary cloudinary;

    public Page<BlogPost> getPosts(int pageNumber, int size, String orderBy) {
        Pageable page = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return postDAO.findAll(page);
    }

    public BlogPost findById(long id) {
        return this.postDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost save(PostPayload newpost) {
        UserPost user = authorService.findById(newpost.userid());
        return postDAO.save(
                new BlogPost(newpost.categoria(), newpost.titolo(),  newpost.cover(), newpost.contenuto(), newpost.tempoDiLettura(), user)
        );
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
    }

    public String findAndPostCover(long id, MultipartFile image)throws IOException {
        BlogPost blogPost=this.findById(id);
        String url = (String) cloudinary.uploader().upload(image.getBytes(),
                ObjectUtils.emptyMap()).get("url");
        blogPost.setCover(url);
        postDAO.save(blogPost);
        return url;
    }

}
