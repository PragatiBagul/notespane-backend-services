package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.Topic;
import notespane.notespanebackendservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {
    List<Topic> findAllByUser(User user);
}