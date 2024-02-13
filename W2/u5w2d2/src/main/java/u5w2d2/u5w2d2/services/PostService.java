package u5w2d2.u5w2d2.services;

import org.springframework.stereotype.Service;
import u5w2d2.u5w2d2.entities.BlogPost;
import u5w2d2.u5w2d2.entities.UserPost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class PostService {
    private List<BlogPost> blogPosts=new ArrayList<>();

    public List<BlogPost> getPosts(){
        return this.blogPosts;
    }

    public BlogPost findById(long id){
        BlogPost found=null;
        for (BlogPost blogPost: this.blogPosts){
            if (blogPost.getId()==id){
                found=blogPost;
            }
        }
        if (found==null)throw new RuntimeException("Blopost non trovato");
        else return found;
    }

    public BlogPost save(BlogPost blogpost){
        Random r=new Random();
        blogpost.setId(r.nextInt(1,1000));
        this.blogPosts.add(blogpost);
        return blogpost;
    }

    public BlogPost findAndUpadate(long id,BlogPost update){
        BlogPost found=null;
        for (BlogPost blogPost: this.blogPosts){
            if (blogPost.getId()==id){
                found=blogPost;
                found.setCategoria(update.getCategoria());
                found.setTitolo(update.getTitolo());
                found.setCover(update.getCover());
                found.setContenuto(update.getContenuto());
                found.setTempoDiLettura(update.getTempoDiLettura());
            }
        }
        if (found==null)throw new RuntimeException("Blopost non trovato");
        else return found;
    }

    public void findAndDelete(long id){
        Iterator<BlogPost> iterator=this.blogPosts.iterator();
        while(iterator.hasNext()){
            BlogPost current= iterator.next();
            if (current.getId()==id){
                iterator.remove();
            }
        }
    }
}
