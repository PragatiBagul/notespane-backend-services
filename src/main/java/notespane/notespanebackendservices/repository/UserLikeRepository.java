package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserLikeRepository extends JpaRepository<UserLike, Long>, JpaSpecificationExecutor<UserLike> {
    void deleteAllByPostId(Long postId);
}