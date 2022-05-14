package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.Saved;
import notespane.notespanebackendservices.model.UserLike;
import notespane.notespanebackendservices.service.SavedService;
import notespane.notespanebackendservices.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("like")
public class UserLikeController {
    @Autowired
    UserLikeService userLikeService;
    @GetMapping("post/{postId}")
    public ResponseEntity<List<UserLike>> fetchUserLikesOnPost(@PathVariable Long postId)
    {
        return new ResponseEntity<>(userLikeService.fetchUserLikesOnPost(postId), HttpStatus.OK);
    }
    @PostMapping("post/{postId}")
    public ResponseEntity<UserLike> userLikeOnPost(@PathVariable Long postId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return new ResponseEntity(userLikeService.userLikeOnPost(uid,postId), HttpStatus.OK);
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity deleteUserLikeOnPost(@PathVariable Long postId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        System.out.println(uid);
        userLikeService.deleteUserLikeOnPost(uid,postId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("comment/{commentId}")
    public ResponseEntity<List<UserLike>> fetchUserLikesOnComment(@PathVariable Long commentId)
    {
        return new ResponseEntity<>(userLikeService.fetchUserLikesOnComment(commentId), HttpStatus.OK);
    }
    @PostMapping("comment/{commentId}")
    public ResponseEntity<UserLike> userLikeOnComment(@PathVariable Long commentId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return new ResponseEntity(userLikeService.userLikeOnComment(uid,commentId), HttpStatus.OK);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity deleteUserLikeOnComment(@PathVariable Long commentId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        System.out.println(uid);
        userLikeService.deleteUserLikeOnComment(uid,commentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
