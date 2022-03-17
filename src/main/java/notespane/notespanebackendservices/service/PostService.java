package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Comment;
import notespane.notespanebackendservices.model.Post;
import notespane.notespanebackendservices.repository.CommentRepository;
import notespane.notespanebackendservices.repository.PostRepository;
import notespane.notespanebackendservices.repository.UserLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserLikeRepository userLikeRepository;

    @Autowired
    CommentRepository commentRepository;
    public Optional<Post> fetchPost(Long postId) {
        return postRepository.findById(postId);
    }

    public Post createPost(String postTitle, String postDescription, String contentType, String category) {
        Post post = new Post();
        post.setPostTitle(postTitle);
        post.setPostDescription(postDescription);
        post.setCategory(category);
        post.setContentType(contentType);
        post.setTimestampOfCreation(new Timestamp((new Date()).getTime()));
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        userLikeRepository.deleteAllByPostId(postId);
        postRepository.deleteById(postId);
    }
}
