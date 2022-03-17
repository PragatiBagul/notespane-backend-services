package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.Topic;
import notespane.notespanebackendservices.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("topic/")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @GetMapping("{id}")
    public ResponseEntity<Topic> fetchTopic(@RequestBody Long topicId)
    {
        return new ResponseEntity(topicService.fetchTopic(topicId),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Topic> updateTopic(@RequestBody long id,@RequestBody String topicName,@RequestBody String topicDescription)
    {
        return new ResponseEntity(topicService.updateTopic(id,topicName,topicDescription), HttpStatus.OK);
    }
}
