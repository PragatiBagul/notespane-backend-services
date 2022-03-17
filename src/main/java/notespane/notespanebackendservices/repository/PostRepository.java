package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}