package u5w2d3.u5w2d3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import u5w2d3.u5w2d3.entities.BlogPost;
import u5w2d3.u5w2d3.entities.PostPayload;
import u5w2d3.u5w2d3.services.PostService;

@RestController
@RequestMapping("/blogPosts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public Page<BlogPost> getallPosts(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size,
                                      @RequestParam(defaultValue = "id") String orderBy) {
        return postService.getPosts(page,size,orderBy);
    }

    @GetMapping("/{id}")
    public BlogPost getSinglePost(@PathVariable long id) {
        return postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost sevePost( @RequestBody PostPayload postPaylod) {
        return postService.save(postPaylod);
    }

    @PutMapping("/{id}")
    public BlogPost findAndUpdate(@PathVariable long id, @RequestBody BlogPost blogPost){
        return postService.findAndUpadate(id,blogPost);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id){
        this.postService.findAndDelete(id);
    }

}