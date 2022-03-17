package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.Comment;
import notespane.notespanebackendservices.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    public void deleteAllByCommentId(Long commentId);

    public void deleteAllByPostId(Long postId);

    public void findAllByPostId(Long postId);

    public void findAllByCommentId(Long commentId);
}