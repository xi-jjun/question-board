package project.questionboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.questionboard.domain.User;
import project.questionboard.domain.UserRank;
import project.questionboard.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	@Transactional
	public boolean join(User user) {
		if (isDuplicatedNickName(user.getNickName())) {
			return false;
		}

		userRepository.save(user);
		return true;
	}

	private boolean isDuplicatedNickName(String nickName) {
		return userRepository.findAll().stream()
				.anyMatch(currentUser -> currentUser.isSameNickName(nickName));
	}

	public User findUser(int id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByNickName(String nickName) {
		return userRepository.findAll().stream()
				.filter(user -> user.isSameNickName(nickName))
				.findFirst();
	}

	/**
	 * remove 기능 나중에 구현해야 함
	 * @return void
	 */

	public List<User> getUserList() {
		List<User> users = userRepository.findAll();
		users.sort((u1, u2) -> u2.getId() - u1.getId());
		return users;
	}

	/**
	 * Test User data
	 */
	@Transactional
	@PostConstruct
	public void init() {
		User user = new User("김재준", "a", "a", UserRank.ADMIN);
		userRepository.save(user);
	}
}
