package project.questionboard.repository;

import project.questionboard.domain.Posting;

import java.util.List;

public interface PostingRepository {
	public void save(Posting posting);

	public Posting findOne(int id);

	public List<Posting> findAll();

	public void remove(int id);
}
