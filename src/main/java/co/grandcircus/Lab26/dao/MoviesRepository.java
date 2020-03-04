package co.grandcircus.Lab26.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.Lab26.entity.Movie;

public interface MoviesRepository extends JpaRepository<Movie,Long> {

	List<Movie> findByCategoryIgnoreCase(String category);
	
	Movie findByTitleIgnoreCase(String title);
	
	List<Movie> findByTitleContainsIgnoreCase(String key);
}
