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
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "topic_description")
    private String topicDescription;

    @NotNull
    @Column(name = "timestamp_of_creation")
    private Timestamp timestampOfCreation;

    @NotNull
    @Column(name = "timestamp_of_update")
    private Timestamp timestampOfUpdate;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Post> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public Timestamp getTimestampOfCreation() {
        return timestampOfCreation;
    }

    public void setTimestampOfCreation(Timestamp timestampOfCreation) {
        this.timestampOfCreation = timestampOfCreation;
    }

    public Timestamp getTimestampOfUpdate() {
        return timestampOfUpdate;
    }

    public void setTimestampOfUpdate(Timestamp timestampOfUpdate) {
        this.timestampOfUpdate = timestampOfUpdate;
    }
}