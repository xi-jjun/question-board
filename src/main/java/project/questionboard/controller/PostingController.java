package project.questionboard.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.questionboard.domain.Posting;
import project.questionboard.domain.User;
import project.questionboard.service.PostingService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostingController {
	private final PostingService postingService;

	@GetMapping("/postings/new")
	public String posting(@SessionAttribute(name = SessionConstants.LOGIN_USER, required = false) User loginUser) {
		if (loginUser == null) {
			return "redirect:/";
		}
		return "addPostingForm";
	}

	@PostMapping("/postings/new")
	public String postingForm(@RequestParam("title") String title,
							  @RequestParam("content") String content,
							  @RequestParam("code") String code,
							  @SessionAttribute(name = SessionConstants.LOGIN_USER, required = false) User loginUser) {
		if (loginUser == null) {
			return "redirect:/";
		}

		Posting newPosting = new Posting(title, content, code, loginUser);
		postingService.posting(newPosting);
		return "redirect:/postings";
	}

	@GetMapping("/postings")
	public String getPostings(@SessionAttribute(name = SessionConstants.LOGIN_USER, required = false) User loginUser,
							  Model model) {
		if (loginUser == null) {
			return "redirect:/";
		}

		List<Posting> postings = postingService.findAll();
		model.addAttribute("postings", postings);
		return "index";
	}

	@GetMapping("/postings/{postingId}")
	public String getPosting(@SessionAttribute(name = SessionConstants.LOGIN_USER, required = false) User loginUser,
							 @PathVariable("postingId") String id, Model model) {
		if (loginUser == null) {
			return "redirect:/";
		}
		int postingId = Integer.parseInt(id);
		Posting posting = postingService.findById(postingId);
		model.addAttribute("posting", posting);
		return "posting";
	}
}
