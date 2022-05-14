package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserLikeRepository extends JpaRepository<UserLike, Long>, JpaSpecificationExecutor<UserLike> {
    void deleteAllByPostId(Long postId);

    List<UserLike> findAllByPostId(Long postId);

    void deleteByUserIdAndPostId(Long id, Long postId);

    List<UserLike> findAllByCommentId(Long commentId);

    void deleteByUserIdAndCommentId(Long id, Long commentId);
}