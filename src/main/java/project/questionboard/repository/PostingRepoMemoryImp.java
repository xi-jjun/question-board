package project.questionboard.repository;

import org.springframework.stereotype.Repository;
import project.questionboard.domain.Posting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class PostingRepoMemoryImp implements PostingRepository {

	private static int id = 0;
	private static final PostingRepoMemoryImp instance = new PostingRepoMemoryImp();
	private Map<Integer, Posting> store = new TreeMap<>(Collections.reverseOrder());

	public PostingRepoMemoryImp() {

	}


	public static PostingRepoMemoryImp getInstance() {
		return instance;
	}

	@Override
	public void save(Posting posting) {
		posting.setId(++id);
		store.put(id, posting);
	}

	@Override
	public Posting findOne(int id) {
		return store.get(id);
	}

	@Override
	public List<Posting> findAll() {
		return new ArrayList<>(store.values());
	}

	@Override
	public void remove(Long id) {
		store.remove(id);
	}

	@Override
	public void clearAll() {
		store.clear();
	}
}
