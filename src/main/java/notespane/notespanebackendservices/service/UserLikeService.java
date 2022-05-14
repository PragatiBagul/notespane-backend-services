package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Comment;
import notespane.notespanebackendservices.model.Post;
import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.model.UserLike;
import notespane.notespanebackendservices.repository.UserLikeRepository;
import notespane.notespanebackendservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserLikeService {

    @Autowired
    UserLikeRepository userLikeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;
    public List<UserLike> fetchUserLikesOnPost(Long postId) {
        List<UserLike> userLikes = userLikeRepository.findAllByPostId(postId);
        return userLikes;
    }

    public UserLike userLikeOnPost(String uid, Long postId) {
        User user = userRepository.findByUid(uid);
        Post post = postService.fetchPost(postId);
        UserLike userLike = new UserLike();
        userLike.setUser(user);
        userLike.setPost(post);
        userLike.setTimestampOfCreation(new Timestamp((new Date()).getTime()));
        return userLike;
    }

    public void deleteUserLikeOnPost(String uid, Long postId) {
        User user = userRepository.findByUid(uid);
        userLikeRepository.deleteByUserIdAndPostId(user.getId(),postId);
    }
    public List<UserLike> fetchUserLikesOnComment(Long commentId) {
        List<UserLike> userLikes = userLikeRepository.findAllByCommentId(commentId);
        return userLikes;
    }

    public UserLike userLikeOnComment(String uid, Long commentId) {
        User user = userRepository.findByUid(uid);
        Comment comment = commentService.fetchComment(commentId);
        UserLike userLike = new UserLike();
        userLike.setUser(user);
        userLike.setComment(comment);
        userLike.setTimestampOfCreation(new Timestamp((new Date()).getTime()));
        return userLike;
    }

    public void deleteUserLikeOnComment(String uid, Long commentId) {
        User user = userRepository.findByUid(uid);
        userLikeRepository.deleteByUserIdAndCommentId(user.getId(),commentId);
    }

}
