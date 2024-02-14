package u5w2d3.u5w2d3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import u5w2d3.u5w2d3.entities.BlogPost;
import u5w2d3.u5w2d3.entities.UserPost;

import java.util.Optional;

@Repository
public interface PostDAO extends JpaRepository<BlogPost, Long> {
    Optional<BlogPost> findByTitolo(String titolo);

}
