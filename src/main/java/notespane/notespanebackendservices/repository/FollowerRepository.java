package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.Follower;
import notespane.notespanebackendservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long>, JpaSpecificationExecutor<Follower> {
    List<User> findFollowerIdByTopicId(Long topicId);

    List<User> findFollowerIdByUserId(Long userId);

    List<User> findUserIdByFollowerId(Long userId);
}