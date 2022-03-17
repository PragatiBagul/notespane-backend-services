package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.Post;
import notespane.notespanebackendservices.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("post/")
public class PostController {
    @Autowired
    PostService postService;
    @GetMapping("{id}")
    public ResponseEntity<Post> fetchPost(@RequestParam(value = "id")Long postId)
    {
        return new ResponseEntity(postService.fetchPost(postId), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<Post> createPost(@RequestBody String postTitle,@RequestBody String postDescription,@RequestBody String contentType,@RequestBody String category)
    {
        return new ResponseEntity(postService.createPost(postTitle,postDescription,contentType,category),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deletePost(@RequestParam(value = "id") Long postId)
    {
        postService.deletePost(postId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
