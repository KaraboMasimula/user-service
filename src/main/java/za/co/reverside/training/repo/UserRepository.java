package za.co.reverside.training.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.training.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public List<User> findByFirstName(String firstName);
	public User findByEmail(String email);
}
