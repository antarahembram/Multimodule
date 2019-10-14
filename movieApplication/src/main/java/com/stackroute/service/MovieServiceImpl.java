package com.stackroute.service;

import com.stackroute.domain.Movie;
import com.stackroute.exception.MovieAlreadyExistsException;
import com.stackroute.exception.MovieNotFoundException;
import com.stackroute.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // to write the logic to use Repository
@Profile("prod")  /* Dummy MovieService will be used when application-prod.properties will be active*/
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;   //  to use Repository properties and store data

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.existsById(movie.getMovieId()))       //if movie exists with that movieId throw exception
        {
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        Movie savedMovie= movieRepository.save(movie);
        if(savedMovie==null)
        {
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        return savedMovie;
    }

    @Override
    public List<Movie> getMovieList() {
        return movieRepository.findAll();
    }


    @Override
    public Movie updateMovie(Movie movie)  throws MovieNotFoundException{
        if(!movieRepository.existsById(movie.getMovieId()))  // if no movie exists with that movieId throw exception
        {
            throw new MovieNotFoundException("Movie is not found");
        }
        Movie updatedMovie=movieRepository.findById(movie.getMovieId()).get();  //get movie by movieId
        if(movie.getMovieTitle()!=null)     //if movieTitle is present then update it
            updatedMovie.setMovieTitle(movie.getMovieTitle());
        if(movie.getGenre()!=null)           //if genre is present then update it
            updatedMovie.setGenre(movie.getGenre());
        if(movie.getLanguage()!=null)        //if language is present then update it
            updatedMovie.setLanguage(movie.getLanguage());
        if(movie.getVoteCount()!=0)          //if voteCount is present then update it
            updatedMovie.setVoteCount(movie.getVoteCount());
        if(movie.getStatus()!=null)          //if status is present then update it
            updatedMovie.setStatus(movie.getStatus());
        if(movie.getBudget().intValue()!=0)     //if budget is present then update it
            updatedMovie.setBudget(movie.getBudget());

        return movieRepository.save(updatedMovie);
    }

    @Override
    public Movie deleteMovie(int movieId) throws MovieNotFoundException {
        if(!movieRepository.existsById(movieId))  // if no movie exists with that movieId throw exception
        {
            throw new MovieNotFoundException("Movie is not found");
        }
        Movie movie=movieRepository.findById(movieId).get();   //get movie by movieId
        if(movie==null)
        {
            throw new MovieNotFoundException("Movie is not found");
        }
        movieRepository.deleteById(movieId);     // delete movie by movieId
        return movie;
    }

    @Override
    public List<Movie> getMovieByName(String movieTitle) throws MovieNotFoundException {
        List<Movie> movies=movieRepository.getMovieByName(movieTitle); //get movie by movieTitle
        if(movies.size()==0) //if movies contains no movie then throw exception
        {
            throw new MovieNotFoundException("Movie is not found");
        }
        return movies;
    }


}