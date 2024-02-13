package u5w2d2.u5w2d2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import u5w2d2.u5w2d2.entities.BlogPost;
import u5w2d2.u5w2d2.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<BlogPost> getallPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public BlogPost getSinglePost(@PathVariable long id) {
        return postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost sevePost(@RequestBody BlogPost blogPost) {
        return postService.save(blogPost);
    }

    @PutMapping("/{id}")
    public BlogPost findAndUpdate(@PathVariable long id, @RequestBody BlogPost blogPost) {
        return postService.findAndUpadate(id, blogPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id) {
        postService.findAndDelete(id);
    }


}
