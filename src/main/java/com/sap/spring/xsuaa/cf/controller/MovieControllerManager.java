package com.sap.spring.xsuaa.cf.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.spring.xsuaa.cf.model.Movie;
import com.sap.spring.xsuaa.cf.repository.MovieRepository;

@RestController
@RequestMapping("manager")
public class MovieControllerManager {

	@Autowired
	MovieRepository movieRepository;
	
	
	
	@GetMapping("list")
	public List<Movie> movieList(){
		return movieRepository.findAll();
	}
	
	
	// Create Movie
	@PostMapping("add")
	public Movie addMovie(@RequestBody Movie movie) {
		return movieRepository.save(movie);
	}

	// Delete Movie
	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete/{id}")
	public boolean deleteMovie(@PathVariable ObjectId id) {
		Optional<Movie> movie = movieRepository.findById(id);
		if (movie.isPresent()) {
			try {
				movieRepository.delete(movie.get());
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
}
