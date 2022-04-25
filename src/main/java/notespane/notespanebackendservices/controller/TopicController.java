package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.Topic;
import notespane.notespanebackendservices.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("topic/")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<List<Topic>> fetchTopics()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return new ResponseEntity(topicService.fetchTopics(uid),HttpStatus.OK);
    }

    @GetMapping("user/{uid}")
    public ResponseEntity<List<Topic>> fetchTopicsOfUser(@PathVariable String uid)
    {
        return new ResponseEntity(topicService.fetchTopics(uid),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Topic> fetchTopic(@RequestBody Long topicId)
    {
        return new ResponseEntity(topicService.fetchTopic(topicId),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Topic> updateTopic(@RequestBody long id,@RequestBody String topicName,@RequestBody String topicDescription)
    {
        return new ResponseEntity(topicService.updateTopic(id,topicName,topicDescription), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<String> createTopic(@RequestBody Topic topic)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return new ResponseEntity(topicService.saveTopic(uid,topic), HttpStatus.OK);
    }
}
