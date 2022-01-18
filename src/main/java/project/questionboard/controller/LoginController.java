package project.questionboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.questionboard.domain.User;
import project.questionboard.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
	private final LoginService loginService;

	@GetMapping("/login")
	public String loginForm(@SessionAttribute(name = SessionConstants.LOGIN_USER, required = false) User loginUser) {
		if (loginUser == null) {
			return "login";
		}

		return "redirect:/postings";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm loginForm,
						BindingResult bindingResult,
						@RequestParam(defaultValue = "/postings") String redirectURL,
						HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		User loginUser = loginService.login(loginForm.getNickName(), loginForm.getPassword());

		if (loginUser == null) {
			bindingResult.reject("login fail", "닉네임 혹은 비밀번호가 틀렸습니다.");
			log.warn("invalid nick name = {}, password = {}", loginForm.getNickName(), loginForm.getPassword());
			return "login";
		}

		log.info("{} succeeded to login by nick name {}", loginUser.getName(), loginUser.getNickName());
		getUserSession(request, loginUser);

		return "redirect:" + redirectURL;
	}

	private void getUserSession(HttpServletRequest request, User loginUser) {
		HttpSession session = request.getSession(); // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
		session.setAttribute(SessionConstants.LOGIN_USER, loginUser); // 세션에 로그인 회원 정보 보관
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return "redirect:/";
	}
}
