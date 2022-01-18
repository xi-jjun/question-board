package project.questionboard.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.questionboard.domain.Posting;
import project.questionboard.repository.PostingRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostingService {
	private final PostingRepository postingRepository;

	@Transactional
	public void posting(Posting posting) {
		postingRepository.save(posting);
	}

	public Posting findById(int id) {
		return postingRepository.findOne(id);
	}

	public List<Posting> findByTitle(String title) {
		return postingRepository.findAll().stream()
				.filter(posting -> posting.getTitle().equals(title))
				.collect(Collectors.toList());
	}

	public List<Posting> findAll() {
		List<Posting> postings = postingRepository.findAll();
		postings.sort((o1, o2) -> o2.getId() - o1.getId());
		return postings;
	}
}
