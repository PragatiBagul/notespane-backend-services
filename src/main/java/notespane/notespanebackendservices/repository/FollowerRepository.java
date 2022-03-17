package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.Follower;
import notespane.notespanebackendservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    public List<User> findUserIdByFollowerId(Long followerId);

    public List<User> findFollowerIdByUserId(Long userId);

    public List<User> findFollowerIdByTopicId(Long topicId);

    public void deleteAllByUserId(Long userId);

    public void deleteAllByFollowerId(Long followerId);

    public void deleteAllByTopicId(Long topicId);
}