package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
}