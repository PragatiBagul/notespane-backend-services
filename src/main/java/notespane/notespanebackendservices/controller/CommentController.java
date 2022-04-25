package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.Comment;
import notespane.notespanebackendservices.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("comment/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("{id}")
    public ResponseEntity<Comment> fetchComment(@RequestParam(value = "id") Long commentId)
    {
        return new ResponseEntity(commentService.fetchComment(commentId), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<Comment> createComment(@RequestBody String activityType,@RequestBody long activityId,@RequestBody long userId,@RequestBody String commentText)
    {
        Comment comment = commentService.createComment(activityType,activityId,userId,commentText);
        return new ResponseEntity(comment,HttpStatus.OK);
    }
    @DeleteMapping("delete")
    public ResponseEntity deleteComment(@RequestBody Long commentId)
    {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
