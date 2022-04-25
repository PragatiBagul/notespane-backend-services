package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Topic;
import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.repository.PostRepository;
import notespane.notespanebackendservices.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;
    public Topic fetchTopic(long topicId) {
        return topicRepository.getById(topicId);
    }
    public Object updateTopic(long id,String topicName, String topicDescription) {
        Topic topic = topicRepository.getById(id);
        topic.setTopicName(topicName);
        topic.setTopicDescription(topicDescription);
        return topicRepository.save(topic);
    }

    public Topic saveTopic(String uid,Topic topic) {
        User user = userService.getUser(uid);
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        topic.setUser(user);
        topic.setTimestampOfCreation(ts);
        topic.setTimestampOfUpdate(ts);
        return topicRepository.save(topic);
    }

    public List<Topic> fetchTopics(String uid) {
        User user = userService.getUser(uid);
        return topicRepository.findAllByUser(user);
    }
}
