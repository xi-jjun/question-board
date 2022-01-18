package project.questionboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.questionboard.domain.User;
import project.questionboard.domain.UserRank;
import project.questionboard.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/users/new")
	public String userForm() {
		return "user-form";
	}

	@PostMapping("/users/new")
	public String signUp(@RequestParam("name") String name,
						 @RequestParam("password") String password,
						 @RequestParam("nickName") String nickName,
						 @RequestParam(defaultValue = "NORMAL") String userRank) {

		User user = new User(name, password, nickName, UserRank.NORMAL);
		boolean joinOk = userService.join(user);

		if (joinOk) {
			log.info("{} succeeded to join by nick name {}.", name, nickName);
			return "redirect:/postings";
		}

		log.warn("fail to join");
		return "redirect:/users/new";
	}
}
