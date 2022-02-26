package notespane.notespanebackendservices.service;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseService {

    @PostConstruct
    public void initializeFirebaseApp() throws IOException {
        InputStream serviceAccount = this.getClass().getResourceAsStream("/notespaneServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://notespane-19f22.asia-south1.firebasedatabase.app").build();

        FirebaseApp.initializeApp(options);
    }

}
