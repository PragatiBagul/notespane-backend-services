package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.Saved;
import notespane.notespanebackendservices.repository.SavedRepository;
import notespane.notespanebackendservices.service.SavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("saved/")
public class SavedController {

    @Autowired
    SavedService savedService;

    @GetMapping
    public ResponseEntity<List<Saved>> fetchSaved()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return new ResponseEntity<>(savedService.fetchSaved(uid), HttpStatus.OK);
    }

    @PostMapping("{postId}")
    public ResponseEntity<Saved> save(@PathVariable Long postId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return new ResponseEntity(savedService.save(uid,postId), HttpStatus.OK);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity delete(@PathVariable Long postId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        savedService.delete(uid,postId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
