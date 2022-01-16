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
import project.questionboard.domain.Posting;
import project.questionboard.repository.PostingRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostingController {
	private final PostingRepository postingRepository;

	@GetMapping("/postings/new")
	public String posting() {
		return "addPostingForm";
	}

	@PostMapping("/postings/new")
	public String postingForm(@RequestParam("title") String title,
							  @RequestParam("content") String content,
							  @RequestParam("code") String code,
							  @RequestParam("writer") String writer,
							  @RequestParam("deletePassword") String deletePassword) {
		log.info("create posting");

		Posting posting = new Posting(title, content, code, writer, deletePassword);
		postingRepository.save(posting);
		return "redirect:/postings";
	}

	@GetMapping("/postings")
	public String getPostings(Model model) {
		log.info("posting list");

		List<Posting> postings = postingRepository.findAll();
		model.addAttribute("postings", postings);
		return "index";
	}

	@GetMapping("/postings/{postingId}")
	public String getPosting(@PathVariable("postingId") String postingId, Model model) {
		log.info("find Posting");

		Posting posting = postingRepository.findOne(Integer.parseInt(postingId));
		model.addAttribute("posting", posting);
		return "posting";
	}
}
