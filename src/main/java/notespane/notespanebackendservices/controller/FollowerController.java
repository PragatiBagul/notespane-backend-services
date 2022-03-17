package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.service.FollowerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("follower")
public class FollowerController {

    @Autowired
    FollowerService followerService;
    @GetMapping("/following/{userId}") //Users followed by userId
    public ResponseEntity<List<User>> fetchFollowing(@RequestParam("userId") Long userId)
    {
        return new ResponseEntity(followerService.fetchFollowing(userId), HttpStatus.OK);
    }
    @GetMapping("/followers/{userId}") //Users who follow userId
    public ResponseEntity<List<User>> fetchFollowers(@RequestParam("userId") Long userId)
    {
        return new ResponseEntity(followerService.fetchFollowers(userId), HttpStatus.OK);
    }
    @GetMapping("/followers/{topicId}")
    public ResponseEntity<List<User>> fetchFollowersForTopic(@RequestParam("userId") Long topicId)
    {
        return new ResponseEntity(followerService.fetchFollowersForTopic(topicId), HttpStatus.OK);
    }
}
