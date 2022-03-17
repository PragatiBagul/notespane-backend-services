package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Topic;
import notespane.notespanebackendservices.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;
    public Topic fetchTopic(long topicId) {
        return topicRepository.getById(topicId);
    }

    public Object updateTopic(long id,String topicName, String topicDescription) {
        Topic topic = topicRepository.getById(id);
        topic.setTopicName(topicName);
        topic.setTopicDescription(topicDescription);
        return topicRepository.save(topic);
    }
}
