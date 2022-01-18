package project.questionboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.questionboard.domain.User;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final UserService userService;

	public User login(String nickName, String password) {
		return userService.findByNickName(nickName)
				.filter(user -> user.correctPassword(password))
				.orElse(null);
	}
}
