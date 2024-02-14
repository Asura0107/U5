package u5w2d3.u5w2d3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import u5w2d3.u5w2d3.services.AuthorService;
import u5w2d3.u5w2d3.entities.UserPost;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<UserPost> getallUser(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "id") String orderBy) {
        return authorService.getUsers(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public UserPost getSingleUser(@PathVariable long id) {
        return authorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserPost seveUser(@RequestBody UserPost UserPost) {
        return authorService.save(UserPost);
    }

    @PutMapping("/{id}")
    public UserPost findAndUpdate(@PathVariable long id, @RequestBody UserPost UserPost) {
        return authorService.findAndUpadate(id, UserPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long id) {
        this.authorService.findAndDelete(id);
    }

}
