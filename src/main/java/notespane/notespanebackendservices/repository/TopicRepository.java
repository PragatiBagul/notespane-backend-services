package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}