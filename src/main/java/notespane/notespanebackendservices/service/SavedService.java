package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Post;
import notespane.notespanebackendservices.model.Saved;
import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.repository.SavedRepository;
import notespane.notespanebackendservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class SavedService {
    @Autowired
    SavedRepository savedRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;
    public List<Saved> fetchSaved(String uid) {
        User user = userRepository.findByUid(uid);
        return savedRepository.findAllByUserId(user.getId());
    }

    public Saved save(String uid, Long postId) {
        User user = userRepository.findByUid(uid);
        Post post = postService.fetchPost(postId);

        Saved saved = new Saved();
        saved.setUser(user);
        saved.setPost(post);
        saved.setTimestampOfCreation(new Timestamp((new Date()).getTime()));
        return savedRepository.save(saved);
    }

    public void delete(String uid, Long postId) {
        User user = userRepository.findByUid(uid);
        savedRepository.deleteByUserIdAndPostId(user.getId(),postId);
    }
}
