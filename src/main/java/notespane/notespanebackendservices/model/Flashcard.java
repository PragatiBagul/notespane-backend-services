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
@Table(name = "flashcard")
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<FlashcardData> flashcardData;

    @NotNull
    @Column(name = "flashcard_name",length = 1000)
    private String flashcardName;

    @Column(name = "flashcard_description", length = 1000)
    private String flashcardDescription;

    @NotNull
    @Column(name = "timestamp_of_creation")
    private Timestamp timestampOfCreation;

    @NotNull
    @Column(name = "timestamp_of_update")
    private Timestamp timestampOfUpdate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlashcardName() {
        return flashcardName;
    }

    public void setFlashcardName(String flashcardName) {
        this.flashcardName = flashcardName;
    }

    public String getFlashcardDescription() {
        return flashcardDescription;
    }

    public void setFlashcardDescription(String flashcardDescription) {
        this.flashcardDescription = flashcardDescription;
    }


    public Set<FlashcardData> getFlashcardData() {
        return flashcardData;
    }

    public void setFlashcardData(Set<FlashcardData> flashcardData) {
        this.flashcardData = flashcardData;
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