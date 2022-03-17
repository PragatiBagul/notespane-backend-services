package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Comment;
import notespane.notespanebackendservices.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment fetchComment(Long commentId) {
        return commentRepository.getById(commentId);
    }

    public Comment createComment(String activityType,long activityId,long userId,String commentText)
    {
        Comment comment = new Comment();
        comment.setCommentText(commentText);
        return commentRepository.save(comment);
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteAllByCommentId(commentId);
        commentRepository.deleteById(commentId);
    }
}
