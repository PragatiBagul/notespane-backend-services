package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Follower;
import notespane.notespanebackendservices.model.Topic;
import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.repository.FollowerRepository;
import notespane.notespanebackendservices.repository.TopicRepository;
import notespane.notespanebackendservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FollowerService {

    @Autowired
    FollowerRepository followerRepository;
    @Autowired
    UserService userService;
    @Autowired
    TopicRepository topicRepository;
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

    public List<Follower> followUser(Long userId, String followerUid) {
        User user = userService.getUser(userId);
        User self = userService.getUser(followerUid);

        List<Topic> topics = topicRepository.findAllByUser(user);
        List<Follower> followers = new ArrayList<>();
        for(Topic topic : topics){
            Follower follower = new Follower();
            follower.setUser(user);
            follower.setTopic(topic);
            follower.setFollower(self);
            followers.add(followerRepository.save(follower));
        }
        return followers;
    }
}
