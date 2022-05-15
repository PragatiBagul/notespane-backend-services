package notespane.notespanebackendservices.controller;

import lombok.extern.slf4j.Slf4j;
import notespane.notespanebackendservices.model.Task;
import notespane.notespanebackendservices.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("task/")
@Slf4j
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() throws ExecutionException, InterruptedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        return new ResponseEntity<>(taskService.fetchTasks(uid), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws InterruptedException, ExecutionException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        return new ResponseEntity(taskService.createTask(task,uid),HttpStatus.OK) ;
    }

    @PutMapping("update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) throws InterruptedException, ExecutionException{
        System.out.println(task.getTaskTitle());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName(); // Firebase uid
        return new ResponseEntity(taskService.updateTask(task,uid),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable String id) throws InterruptedException, ExecutionException{
        taskService.deleteTask(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
