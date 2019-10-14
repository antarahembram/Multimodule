package com.stackroute.controller;

import com.stackroute.domain.Movie;
import com.stackroute.exception.MovieAlreadyExistsException;
import com.stackroute.exception.MovieNotFoundException;
import com.stackroute.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   // to use the class as RestController
@RequestMapping("v1/api/")   // url starts with v1/api/
public class MovieController {
    @Autowired
    private MovieService movieService;   // to use methods of  MovieService
    private ResponseEntity responseEntity;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("movie") // http method=post with url movie - store a movie in database
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) throws MovieAlreadyExistsException,Exception
    {
        try{
            responseEntity=new ResponseEntity<Movie>(movieService.saveMovie(movie), HttpStatus.CREATED);
        }
        catch (MovieAlreadyExistsException e){
            responseEntity=new ResponseEntity<String>("Movie already exists",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("movies") // http method=get with url movies - get list of movies
    public ResponseEntity<?> getMovieList() throws Exception
    {
        responseEntity=new ResponseEntity<List<Movie>>(movieService.getMovieList(), HttpStatus.OK);
        return responseEntity;
    }



    @PatchMapping("movies")  // http method=patch with url movies - update a movie with matching movieId
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie) throws MovieNotFoundException,Exception
    {
        //Global exception handling- one example
        responseEntity=new ResponseEntity<Movie>(movieService.updateMovie(movie), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("movie/{movieId}")   // http method=delete with url movie/movieId - delete movie  by movieId
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") int movieId)
    {
        try{
            responseEntity=new ResponseEntity<Movie>(movieService.deleteMovie(movieId), HttpStatus.OK);
        }
        catch (MovieNotFoundException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @GetMapping("movies/{movieTitle}")   // http method=get with url movies/movieTitle - get movie or movies  by movieTitle
    public ResponseEntity<?> getMovieByName(@PathVariable("movieTitle") String movieTitle) throws MovieNotFoundException,Exception
    {
        try{
            responseEntity=new ResponseEntity<List<Movie>>(movieService.getMovieByName(movieTitle), HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity=new ResponseEntity<String>("Searching is failed",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


}
