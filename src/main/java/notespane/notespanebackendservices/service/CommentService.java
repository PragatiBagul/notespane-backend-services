package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Comment;
import notespane.notespanebackendservices.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Optional<Comment> fetchComment(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public Comment createComment(String activityType,long activityId,long userId,String commentText)
    {
        Comment comment = new Comment();
        comment.setCommentText(commentText);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
