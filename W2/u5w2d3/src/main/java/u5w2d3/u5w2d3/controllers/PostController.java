package u5w2d3.u5w2d3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import u5w2d3.u5w2d3.entities.BlogPost;
import u5w2d3.u5w2d3.entities.PostPayload;
import u5w2d3.u5w2d3.exception.BadRequestException;
import u5w2d3.u5w2d3.services.PostService;

import java.io.IOException;

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
    public BlogPost sevePost(@RequestBody @Validated PostPayload postPaylod, BindingResult validation) {
        if (validation.hasErrors()){
            throw  new BadRequestException(validation.getAllErrors());
        }
        return postService.save(postPaylod);
    }

    @PutMapping("/{id}")
    public BlogPost findAndUpdate(@PathVariable long id, @RequestBody @Validated BlogPost blogPost,BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return postService.findAndUpadate(id,blogPost);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id){
        this.postService.findAndDelete(id);
    }

    @PostMapping("/{id}/upload")
    public String uploadCover(@PathVariable long id, @RequestParam("cover") MultipartFile image) throws IOException {
        return this.postService.findAndPostCover(id,image);
    }
}