package project.questionboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.questionboard.domain.User;

@Controller
public class MainController {
	@GetMapping("/")
	public String home(
			@SessionAttribute(name = SessionConstants.LOGIN_USER, required = false) User loginUser,
			Model model) {
		if (loginUser == null) {
			return "redirect:/login";
		}

		return "redirect:/postings";
	}
}
