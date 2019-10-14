package com.stackroute.service;


import com.stackroute.domain.Movie;
import com.stackroute.exception.MovieAlreadyExistsException;
import com.stackroute.exception.MovieNotFoundException;


import java.util.List;

public interface MovieService {
    //To save the movie in repository
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;

    //to get the list of stored movies
    public List<Movie> getMovieList();

    //to update the movie
    public Movie updateMovie(Movie movie) throws MovieNotFoundException ;

    //to delete the movie with given movieId
    public Movie deleteMovie(int movieId) throws MovieNotFoundException;

    //to search movie by movieTitle
    public List<Movie> getMovieByName(String movieTitle) throws  MovieNotFoundException;

}

