package notespane.notespanebackendservices.service;

import notespane.notespanebackendservices.model.Flashcard;
import notespane.notespanebackendservices.repository.FlashcardDataRepository;
import notespane.notespanebackendservices.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FlashcardService {
    @Autowired
    FlashcardRepository flashcardRepository;

    @Autowired
    UserService userService;

    @Autowired
    FlashcardDataRepository flashcardDataRepository;

    public Flashcard fetchFlashcard(long flashcardId) {
        return flashcardRepository.getById(flashcardId);
    }

    public List<Flashcard> fetchFlashcards(){
        return flashcardRepository.findAll();
    }

    public Object updateFlashcard(long id,String flashcardName, String flashcardDescription) {
        Flashcard flashcard = flashcardRepository.getById(id);
        flashcard.setFlashcardName(flashcardName);
        flashcard.setFlashcardDescription(flashcardDescription);
        return flashcardRepository.save(flashcard);
    }

    public Flashcard saveFlashcard(Flashcard flashcard) {
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        flashcard.setTimestampOfCreation(ts);
        flashcard.setTimestampOfUpdate(ts);
        return flashcardRepository.save(flashcard);
    }

}
