package notespane.notespanebackendservices.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import notespane.notespanebackendservices.model.Post;
import notespane.notespanebackendservices.service.PostService;
import notespane.notespanebackendservices.util.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("post/")
@Slf4j
public class PostController {
    @Autowired
    PostService postService;
    @GetMapping("")
    public ResponseEntity<List<Post>> getFeed()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        return new  ResponseEntity(postService.getFeed(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Post> fetchPost(@RequestParam(value = "id")Long postId)
    {
        return new ResponseEntity(postService.fetchPost(postId), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<Post> createPost(@RequestBody Post post)
    {
        return new ResponseEntity(postService.createPost(post),HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deletePost(@RequestParam(value = "id") Long postId)
    {
        postService.deletePost(postId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("ofTopic/{topicId}")
    public ResponseEntity<List<Post>> fetchPostsByTopic(@PathVariable Long topicId)
    {
        List<Post> posts = postService.findPostsByTopic(topicId);
        return new ResponseEntity(posts,HttpStatus.OK);
    }

    @PostMapping(
            path = "{postId}/content/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadPostContentImage(@PathVariable("postId") Long postId,
                                       @RequestParam("file")MultipartFile file){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        postService.uploadPostContent(uid,postId,file, ContentType.IMAGE);
    }
    @PostMapping(
            path = "{postId}/content/attachment",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserPostContentFile(@PathVariable("postId") Long postId,
                                       @RequestParam("file")MultipartFile file){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        postService.uploadPostContent(uid,postId,file, ContentType.FILE);
    }
    @GetMapping(path = "{postId}/content/image")
    public ResponseEntity<byte[]> downloadPostContentImage(@PathVariable("postId") Long postId){
        S3Object object = postService.downloadPostContent(postId);
        S3ObjectInputStream inputStream = object.getObjectContent();

        try
        {
            byte[] image = Base64.getEncoder().encode(IOUtils.toByteArray(inputStream));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +object.getKey() + "\"")
                    .body(image);
        }
        catch(AmazonServiceException | IOException e)
        {
            throw new IllegalStateException("Failed to download file to s3",e);
        }

    }
}
