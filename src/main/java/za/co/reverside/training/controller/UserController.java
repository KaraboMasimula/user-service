package za.co.reverside.training.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.reverside.training.model.User;
import za.co.reverside.training.repo.UserRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserRepository repository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public User verifyUser(@RequestParam(name="email") String email, @RequestParam(name="password") String password) {
		
		User user = repository.findByEmail(email);
		if(user != null ) {
			
			if(user.getPassword().equals(password)) {
				return user;
			}else {
				throw new RuntimeException("Passwords Don't Match");
			}
			
		} 
		throw new RuntimeException("User Not Found");
	}
	
	@RequestMapping(value="users", method=RequestMethod.GET)
	public List<User> getUsers(@RequestParam(name = "firstName", required= false) String firstName) {
		logger.info("Get Users");
		List<User> users = new ArrayList<>();
		if(firstName != null) {
			users = repository.findByFirstName(firstName);
		} else {
			users = repository.findAll();
		}
		logger.info("Get Users Total:" + users.size());
		return users;
	}

	@RequestMapping(value="users", method=RequestMethod.POST)
	public void postUser(@RequestBody User user) {
		logger.info("Adding User");
		repository.save(user);
	}

	@RequestMapping(value="users/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable("id") long id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			return user.get();
		} 
		throw new RuntimeException("User Not Found");
	}
	
	@RequestMapping(value="users/{id}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") long id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			repository.delete(user.get());
		}
		throw new RuntimeException("User Not Found");
	}

	
	@RequestMapping(value="users/{id}", method=RequestMethod.PUT)
	public void updateUser(@PathVariable("id") long id, @RequestBody User newUser) {
		
		logger.info("Updating User");
		Optional<User> userData = repository.findById(id);
		
		if(userData.isPresent()) {
			
			User oldUser = userData.get();
			oldUser.setFirstName(newUser.getFirstName());
			oldUser.setLastName(newUser.getLastName());
			oldUser.setMarried(newUser.isMarried());
			oldUser.setDateOfBirth(newUser.getDateOfBirth());
			oldUser.setGender(newUser.getGender());
			oldUser.setDateOfMarriage(newUser.getDateOfMarriage());
			oldUser.setPhoto(newUser.getPhoto());
			oldUser.setEmail(newUser.getEmail());
			oldUser.setPassword(newUser.getPassword());
			
			repository.save(oldUser);
		}else {
			throw new RuntimeException("User Not Found");
		}
		
	}
	
}
