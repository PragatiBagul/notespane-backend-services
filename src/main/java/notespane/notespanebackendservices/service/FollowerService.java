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
    @Autowired
    UserRepository userRepository;
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
        User user = userRepository.findById(userId);
        User follower = userRepository.findById(followerUid);

        List<Topic> topics = topicRepository.findAllByUser(user);
        List<Follower> followers = new ArrayList<>();
        for(Topic topic : topics){
            Follower follower = new Follower();
            follower.setUser(user);
            follower.setTopic(topic);
            follower.setFollower(follower);
            followers.add(followerRepository.save(follower));
        }
        return followers;
    }
}
