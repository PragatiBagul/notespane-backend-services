package notespane.notespanebackendservices.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @NotNull
    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_description")
    private String postDescription;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "content_link")
    private String contentLink;

    @Column(name = "category")
    private String category;

    @NotNull
    @Column(name = "timestamp_of_creation")
    private Timestamp timestampOfCreation;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<UserLike> userLikes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getTimestampOfCreation() {
        return timestampOfCreation;
    }

    public void setTimestampOfCreation(Timestamp timestampOfCreation) {
        this.timestampOfCreation = timestampOfCreation;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<UserLike> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Set<UserLike> userLikes) {
        this.userLikes = userLikes;
    }
}