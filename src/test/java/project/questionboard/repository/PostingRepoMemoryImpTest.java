package project.questionboard.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.questionboard.domain.Posting;

import java.util.List;

class PostingRepoMemoryImpTest {
	private final PostingRepository postingRepository = PostingRepoMemoryImp.getInstance();

	@BeforeEach
	void makeDummy() {
		Posting posting1 = new Posting("제목1", "본문1", "코드1", "작성자1", "삭제비번1");
		Posting posting2 = new Posting("제목2", "본문2", "코드2", "작성자2", "삭제비번2");
		Posting posting3 = new Posting("제목3", "본문3", "코드3", "작성자3", "삭제비번3");
		Posting posting4 = new Posting("제목4", "본문4", "코드4", "작성자4", "삭제비번4");
		postingRepository.save(posting1);
		postingRepository.save(posting2);
		postingRepository.save(posting3);
		postingRepository.save(posting4);
	}

	@Test
	void postings() {
		List<Posting> postings = postingRepository.findAll();
		postings.forEach(System.out::println);
	}
}
