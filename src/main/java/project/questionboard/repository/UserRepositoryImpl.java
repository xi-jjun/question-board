package project.questionboard.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.questionboard.domain.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final EntityManager em;

	@Override
	@Transactional
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public User findById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return em.createQuery("select u from User u", User.class)
				.getResultList();
	}

	@Override
	@Transactional
	public void remove(int id) {
		User user = findById(id);
		em.remove(user);
	}
}
