package u5w2d2.u5w2d2.services;

import org.springframework.stereotype.Service;
import u5w2d2.u5w2d2.entities.UserPost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class AuthorService {
    private List<UserPost> userPosts = new ArrayList<>();

    public List<UserPost> getUsers() {
        return this.userPosts;
    }

    public UserPost findById(long id) {
        UserPost found = null;
        for (UserPost userPost : this.userPosts) {
            if (userPost.getId() == id) {
                found = userPost;
            }
        }
        if (found == null) throw new RuntimeException("User non trovato");
        else return found;
    }

    public UserPost save(UserPost userpost) {
        Random r = new Random();
        userpost.setId(r.nextInt(1, 1000));
        userpost.setAvatar("https://ui-avatars.com/api/?name="+ userpost.getNome() + "+" + userpost.getCognome());
        this.userPosts.add(userpost);
        return userpost;
    }

    public UserPost findAndUpadate(long id, UserPost update) {
        UserPost found = null;
        for (UserPost userPost : this.userPosts) {
            if (userPost.getId() == id) {
                found = userPost;
                found.setNome(update.getNome());
                found.setCognome(update.getCognome());
                found.setEmail(update.getEmail());
                found.setDataDiNascita(update.getDataDiNascita());
            }
        }
        if (found == null) throw new RuntimeException("User non trovato");
        else return found;
    }

    public void findAndDelete(long id) {
        Iterator<UserPost> iterator = this.userPosts.iterator();
        while (iterator.hasNext()) {
            UserPost current = iterator.next();
            if (current.getId() == id) {
                iterator.remove();
            }
        }
    }
}
