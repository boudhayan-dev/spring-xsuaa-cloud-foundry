package com.sap.spring.xsuaa.cf.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.test.JwtGenerator;
import com.sap.spring.xsuaa.cf.model.Movie;
import com.sap.spring.xsuaa.cf.repository.MovieRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerEmployeeTest {
	
	@MockBean
	MovieRepository movieRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
    private XsuaaServiceConfiguration xsuaaServiceConfiguration;

	private Movie movie;
	private String jwt_employee;
	
	@BeforeEach
	public void setUp() {
		
		// Set JWT token for Employee endpoints
		jwt_employee =  new JwtGenerator()
							.setUserName("boudhayan.dev@example.com")
							.addScopes(xsuaaServiceConfiguration.getAppId() +  ".Employee")
							.getTokenForAuthorizationHeader();
		
		movie = Movie.builder()
				._id(new ObjectId())
				.name("Interstellar")
				.description("Interstellar movie")
				.date("22-10-2019")
				.seats(10)
				.build();
		
		when(movieRepository.findById(any())).thenReturn(Optional.of(movie));
		when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));

	}
	
    
	
//	@DisplayName("TEST - tokenInfo (Oauth)")
//	@Test
//	void getTokenInfoTest() throws Exception {	
//		mockMvc.perform(get("/employee/tokenInfo").header(HttpHeaders.AUTHORIZATION, jwt_employee))
//					.andExpect(status().isOk());
//	}
	
	
//	@DisplayName("TEST - tokenInfo 403 (Oauth)")
//	@Test
//	void getTokenInfoTest403() throws Exception {
//		mockMvc.perform(get("/employee/tokenInfo"))
//		.andExpect(status().is4xxClientError());
//	}
	
	
	@DisplayName("TEST - getMovieById (Oauth)")
	@Test
	void getMovieById() throws Exception {
		mockMvc.perform(get("/employee/movie/" + movie.get_id()).header(HttpHeaders.AUTHORIZATION, jwt_employee))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$._id", is(movie.get_id()) ));
	}
	
	
	@DisplayName("TEST - getMovieList (Oauth)")
	@Test
	void getMovieList() throws Exception {
		mockMvc.perform(get("/employee/movie/list").header(HttpHeaders.AUTHORIZATION, jwt_employee))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(1) ));
	}
	
	@DisplayName("TEST - getMovieList Different date (Oauth)")
	@Test
	void getMovieListDifferentDate() throws Exception {
		mockMvc.perform(get("/employee/movie/list/19-10-2019").header(HttpHeaders.AUTHORIZATION, jwt_employee))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(0) ));
	}
	
	
}
