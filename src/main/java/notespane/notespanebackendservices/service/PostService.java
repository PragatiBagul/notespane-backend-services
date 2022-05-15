package notespane.notespanebackendservices.service;

import com.amazonaws.services.s3.model.S3Object;
import notespane.notespanebackendservices.bucket.BucketName;
import notespane.notespanebackendservices.model.Post;
import notespane.notespanebackendservices.model.Topic;
import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.repository.CommentRepository;
import notespane.notespanebackendservices.repository.PostRepository;
import notespane.notespanebackendservices.repository.TopicRepository;
import notespane.notespanebackendservices.repository.UserLikeRepository;
import notespane.notespanebackendservices.util.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import static org.apache.http.entity.ContentType.*;
@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserLikeRepository userLikeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserService userService;
    @Autowired
    FileStore fileStore;
    public Post fetchPost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(Post post) {
        post.setTimestampOfCreation(new Timestamp((new Date()).getTime()));
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        userLikeRepository.deleteAllByPostId(postId);
        postRepository.deleteById(postId);
    }

    public List<Post> findPostsByTopic(Long topicId) {
        return postRepository.findAllByTopicId(topicId);
    }

    public void uploadPostContent(String uid, Long postId, MultipartFile file, ContentType contentType) {
        // 1. Check if image is not empty
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file ["+file.getSize()+"]");
        }

        // 2. If file is an image
        if(!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image");
        }

        // 3. The post exists in our database
        Post post = fetchPost(postId);

        //4. Fetch the user
        User user = userService.getUser(uid);

        // 5. Grab metadata of file if any
        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));

        // 6. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),post.getId());
        String fileName = String.format("%s",String.valueOf(UUID.randomUUID()));

        try {
            fileStore.save(path,fileName,Optional.of(metadata),file.getInputStream());
            post.setContentType(String.valueOf(contentType));
            post.setContentLink(String.format("%s",fileName));
            postRepository.save(post); 
        }catch(IOException e)
        {
            throw new IllegalStateException(e);
        }
    }
    public S3Object downloadPostContent(Long postId) {
        Post post = fetchPost(postId);
        String[] content = post.getContentLink().split("/");
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName()
                ,postId
        );

        String key = content[0];
        return fileStore.download(path,key);
    }

    public List<Post> getFeed() {

        //List<Post> posts = postRepository.findAll();
        List<Post> posts = new ArrayList<>();
        return posts;
    }
}
