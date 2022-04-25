package notespane.notespanebackendservices.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("")
    public ResponseEntity<User> get() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        System.out.println(uid);
        User user = userService.getUser(uid);
        System.out.println( "The logged in user is "+uid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("{uid}")
    public ResponseEntity<User> get(@PathVariable String uid) throws Exception {
        User user = userService.getUser(uid);
        System.out.println( "The logged in user is "+uid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<HttpStatus> save() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
        System.out.println(uid);
        System.out.println("User Signed In : User Name is : "+userRecord.getDisplayName());
        User user = new User();
        user.setEmail(userRecord.getEmail());
        user.setName(userRecord.getDisplayName());
        user.setUid(uid);
        user.setProfileImage(userRecord.getPhotoUrl());
        user.setLastLogin(userRecord.getUserMetadata().getLastSignInTimestamp());
        User savedUser = userService.save(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<User> update(@RequestBody User userBody) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        System.out.println(uid);
        User user = userService.update(uid,userBody);
        System.out.println( "The logged in user is "+uid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> fetchAllUsers()
    {
        return new ResponseEntity<List<User>>(userService.fetchAllUsers(),HttpStatus.OK);
    }
}