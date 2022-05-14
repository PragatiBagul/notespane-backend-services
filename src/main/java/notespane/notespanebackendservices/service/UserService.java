package notespane.notespanebackendservices.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import notespane.notespanebackendservices.model.User;
import notespane.notespanebackendservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(String uid) throws FirebaseAuthException {
        User user = userRepository.findByUid(uid);
        if(user != null)
        {
            return user;
        }
        else {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
            user = new User();
            user.setEmail(userRecord.getEmail());
            user.setName(userRecord.getDisplayName());
            user.setUid(uid);
            user.setLastLogin(userRecord.getUserMetadata().getLastSignInTimestamp());
            return userRepository.save(user);
        }
    }
    public User getUser(String uid)
    {
        return userRepository.findByUid(uid);
    }

    public User getUser(Long id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public User update(String uid, User userBody) {
        User user = userRepository.findByUid(uid);
        user.setName(userBody.getName());
        user.setEmail(userBody.getEmail());
        user.setWebsiteURL(userBody.getWebsiteURL());
        user.setLinkedinURL(userBody.getLinkedinURL());
        user.setFacebookURL(userBody.getFacebookURL());
        user.setPronoun(userBody.getPronoun());
        user.setQualification(userBody.getQualification());
        return userRepository.save(user);
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }
}
