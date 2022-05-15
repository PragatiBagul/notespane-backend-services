package notespane.notespanebackendservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.*;
import notespane.notespanebackendservices.model.Mocktest;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class MocktestService {

	public Mocktest createMocktest(Mocktest mocktest,String uid) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		String id = dbFirestore.collection("mockTests").document().getId();
		mocktest.setId(id);
		mocktest.setUid(uid);
		dbFirestore.collection("mockTests").document(id).set(mocktest);
		return mocktest;
	}

	public Mocktest getMocktest(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection("task_db").document(id);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		Mocktest mocktest;
		System.out.println(dbFirestore.collection("task_db").document(id).get());
		if(document.exists()) {
			mocktest = document.toObject(Mocktest.class);
			return mocktest;
		}
		return null;
	}

	public String updateMocktest(Mocktest mocktest,String uid) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		mocktest.setUid(uid);
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("mockTests").document(mocktest.getId()).set(mocktest);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public String deleteMocktest(String id) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("mockTests").document(id).delete();
		return "successfully deleted "+id;
	}

    public List<Mocktest> getMocktests(String uid) throws ExecutionException, InterruptedException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("_db").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<Mocktest> mocktests = new ArrayList<>();;
		for (QueryDocumentSnapshot document : documents) {
			if(document.toObject(Mocktest.class).getUid()!=null && document.toObject(Mocktest.class).getUid().equals(uid)) {
				mocktests.add(document.toObject(Mocktest.class));
			}
		}
		return mocktests;
    }

	public List<Mocktest> getPublicMocktests(String uid) throws ExecutionException, InterruptedException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("_db").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<Mocktest> mocktests = new ArrayList<>();;
		for (QueryDocumentSnapshot document : documents) {
			if(document.toObject(Mocktest.class).getUid()!=uid && document.toObject(Mocktest.class).getMode().equalsIgnoreCase("public")) {
				mocktests.add(document.toObject(Mocktest.class));
			}
		}
		return mocktests;
	}
}
