package com.sap.spring.xsuaa.cf.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sap.spring.xsuaa.cf.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, ObjectId>{
	
	List<Movie> findAllByDate(String date); 

}
