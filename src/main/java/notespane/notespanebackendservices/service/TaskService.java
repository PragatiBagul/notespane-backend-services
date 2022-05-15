package notespane.notespanebackendservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.*;
import notespane.notespanebackendservices.model.Task;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class TaskService {

	public String createTask(Task task,String uid) throws InterruptedException, ExecutionException {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		String id = dbFirestore.collection("task_db").document().getId();
		task.setDocumentId(id);
		task.setUid(uid);
		dbFirestore.collection("task_db").document(id).set(task);
		return "task added successfully";
	}

	public Task getTask(String taskId) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection("task_db").document(taskId);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		Task task;
		System.out.println(dbFirestore.collection("task_db").document(taskId).get());
		if(document.exists()) {
			task = document.toObject(Task.class);
			return task;
		}
		return null;
	}

	public List<Task> fetchTasks(String uid) throws InterruptedException, ExecutionException
	{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("task_db").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<Task> allTasks = new ArrayList<>();;
		for (QueryDocumentSnapshot document : documents) {
			if(document.toObject(Task.class).getUid()!=null && document.toObject(Task.class).getUid().equals(uid)) {
				allTasks.add(document.toObject(Task.class));
			}
		}
		return allTasks;
	}
	public String updateTask(Task task,String uid) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		task.setUid(uid);
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("task_db").document(task.getDocumentId()).set(task);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public String deleteTask(String taskId) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("task_db").document(taskId).delete();
		return "successfully deleted"+taskId;
	}

}

