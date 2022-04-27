package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.Flashcard;
import notespane.notespanebackendservices.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("flashcard/")
public class FlashcardController {
    @Autowired
    private FlashcardService flashcardService;

    @GetMapping
    public ResponseEntity<List<Flashcard>> fetchFlashcards()
    {
        return new ResponseEntity(flashcardService.fetchFlashcards(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Flashcard> updateFlashcard(@RequestBody long id,@RequestBody String flashcardName,@RequestBody String flashcardDescription)
    {
        return new ResponseEntity(flashcardService.updateFlashcard(id,flashcardName,flashcardDescription), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<String> createFlashcard(@RequestBody Flashcard flashcard)
    {
        return new ResponseEntity(flashcardService.saveFlashcard(flashcard), HttpStatus.OK);
    }
}
