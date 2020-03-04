package co.grandcircus.Lab26;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.Lab26.dao.MoviesDao;
import co.grandcircus.Lab26.dao.MoviesRepository;
import co.grandcircus.Lab26.entity.Movie;

@RestController
public class MoviesApiController {

	@Autowired
	MoviesRepository movieRepo;

	@Autowired
	MoviesDao movieDao;

	@GetMapping("/movies")
	public List<Movie> listMovies(@RequestParam(value = "category", required = false) String category) {
		if (category == null) {
			List<Movie> movies = movieRepo.findAll();
			return movies;
		} else {
			List<Movie> movieCat = movieRepo.findByCategoryIgnoreCase(category);
			return movieCat;
		}

	}

	@GetMapping("/movies/random")
	public Movie randMovie(@RequestParam(value = "category", required = false) String category) {
		if (category == null) {
			List<Movie> movies = movieRepo.findAll();
			Movie movie = movies.get((int) (Math.random() * movies.size()));
			return movie;
		} else {
			List<Movie> movieCat = movieRepo.findByCategoryIgnoreCase(category);
			Movie moviec = movieCat.get((int) (Math.random() * movieCat.size()));
			return moviec;
		}

	}

	@GetMapping("/movies/randomlist")
	public List<Movie> randMovieL(@RequestParam("count") int count) {
		List<Movie> movies = movieRepo.findAll();
		List<Movie> randMovies = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Movie movie = movies.get((int) (Math.random() * movies.size()));
			movies.remove(movie);
			randMovies.add(movie);
		}
		return randMovies;
	}

	@GetMapping("/movies/movie")
	public Movie movie(@RequestParam("title") String title) {
		Movie movie = movieRepo.findByTitleIgnoreCase(title);
		return movie;
	}

	@GetMapping("/categories")
	public Set<String> categories() {

		return movieDao.categories();
	}

	@GetMapping("/movies/search")
	public List<Movie> search(@RequestParam("title") String key) {
		List<Movie> movies =movieRepo.findByTitleContainsIgnoreCase(key);
		
		return movies;
	}

}
