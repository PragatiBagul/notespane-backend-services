package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.FlashcardData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FlashcardDataRepository extends JpaRepository<FlashcardData, Long>, JpaSpecificationExecutor<FlashcardData> {
}