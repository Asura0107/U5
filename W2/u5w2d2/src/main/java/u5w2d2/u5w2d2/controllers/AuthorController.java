package u5w2d2.u5w2d2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import u5w2d2.u5w2d2.entities.UserPost;
import u5w2d2.u5w2d2.services.AuthorService;

import java.util.List;
@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<UserPost> getallUser(){
        return authorService.getUsers();
    }

    @GetMapping("/{id}")
    public UserPost getSingleUser(@PathVariable long id){
        return authorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserPost seveUser(@RequestBody UserPost UserPost){
        return authorService.save(UserPost);
    }

    @PutMapping("/{id}")
    public UserPost findAndUpdate(@PathVariable long id, @RequestBody UserPost UserPost){
        return authorService.findAndUpadate(id,UserPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id){
         this.authorService.findAndDelete(id);
    }

}
