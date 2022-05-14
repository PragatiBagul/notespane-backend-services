package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.FlashcardData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardDataRepository extends JpaRepository<FlashcardData, Long> {
}