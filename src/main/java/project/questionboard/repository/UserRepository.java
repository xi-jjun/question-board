package project.questionboard.repository;

import project.questionboard.domain.User;

import java.util.List;

public interface UserRepository {
	public void save(User user);

	public User findById(int id);

	public List<User> findAll();

	public void remove(int id);
}
