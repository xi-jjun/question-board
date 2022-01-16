package project.questionboard.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.questionboard.domain.Posting;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostingRepositoryMySql implements PostingRepository{
	private final EntityManager em;

	@Override
	@Transactional
	public void save(Posting posting) {
		em.persist(posting);
	}

	@Override
	public Posting findOne(int id) {
		return em.find(Posting.class, id);
	}

	@Override
	public List<Posting> findAll() {
		List<Posting> postings = em.createQuery("select p from Posting p", Posting.class)
				.getResultList();
		postings.sort(Collections.reverseOrder());
		return postings;
	}

	@Override
	@Transactional
	public void remove(int id) {
		Posting posting = findOne(id);
		em.remove(posting);
	}
}
