package notespane.notespanebackendservices.controller;


import java.util.List;
import java.util.concurrent.ExecutionException;

import notespane.notespanebackendservices.model.Mocktest;
import notespane.notespanebackendservices.service.MocktestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mocktests/")
@CrossOrigin("http://localhost:3000")
public class MocktestController {

	@Autowired
	public MocktestService mocktestService;

	@PostMapping("/create")
	public ResponseEntity<Mocktest> createMocktest(@RequestBody Mocktest mocktest) throws InterruptedException, ExecutionException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String uid = authentication.getName(); // Firebase uid
		System.out.println(uid);
		return new ResponseEntity(mocktestService.createMocktest(mocktest,uid), HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Mocktest>> getMocktests() throws InterruptedException, ExecutionException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String uid = authentication.getName(); // Firebase uid
		return new ResponseEntity(mocktestService.getMocktests(uid),HttpStatus.OK);
	}

	@GetMapping("/getPublic")
	public ResponseEntity<List<Mocktest>> getPublicMocktests() throws InterruptedException, ExecutionException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String uid = authentication.getName(); // Firebase uid
		return new ResponseEntity(mocktestService.getPublicMocktests(uid),HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<List<Mocktest>> getMocktest(@PathVariable String id) throws InterruptedException, ExecutionException{
		return new ResponseEntity(mocktestService.getMocktest(id),HttpStatus.OK);
	}

	@PutMapping("/update")
	public String updateMocktest(@RequestBody Mocktest mocktest) throws InterruptedException, ExecutionException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String uid = authentication.getName(); // Firebase uid
		return mocktestService.updateMocktest(mocktest,uid);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteMocktest(@PathVariable String id) throws InterruptedException, ExecutionException{
		return mocktestService.deleteMocktest(id);
	}
}
