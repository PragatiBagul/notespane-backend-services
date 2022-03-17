package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FollowerService {

    @Autowired
    FollowerRepository followerRepository;

    public List<User> fetchFollowing(Long userId)
    {
        return followerRepository.findUserIdByFollowerId(userId);
    }

    public List<User> fetchFollowers(Long userId) {
        return followerRepository.findFollowerIdByUserId(userId);
    }

    public List<User> fetchFollowersForTopic(Long topicId) {
        return followerRepository.findFollowerIdByTopicId(topicId);
    }
}
