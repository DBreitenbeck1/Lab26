package co.grandcircus.Lab26.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional


public class MoviesDao {
	
	@PersistenceContext
	private EntityManager em;

	
	public Set<String> categories() {
		
		List<String> categories = em.createQuery("SELECT DISTINCT category FROM Movie", 
				String.class).getResultList(); 						
		return new TreeSet<>(categories);
	}

}
