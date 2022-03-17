package notespane.notespanebackendservices.controller;

import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<User> get() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        User user = userRepository.getById(uid);
        System.out.println( "The logged in user is "+user.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
        System.out.println("User Signed In : User Name is : "+userRecord.getDisplayName());
        User user = new User();
        user.setEmail(userRecord.getEmail());
        user.setName(userRecord.getDisplayName());
        user.setUid(uid);
        user.setLastLogin(userRecord.getUserMetadata().getLastSignInTimestamp());
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}