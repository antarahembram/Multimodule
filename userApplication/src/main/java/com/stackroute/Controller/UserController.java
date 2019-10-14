package com.stackroute.Controller;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsExecption;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController  // to use the class as RestController
@RequestMapping("v1/api/") // url starts with v1/api/
public class UserController {
    @Autowired
    private UserService userService;
    private ResponseEntity responseEntity;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")  // http method=post with url user - store a user in database
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsExecption,Exception
    {
        try{
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Success", HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsExecption e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("users") // http method=get with url users - get list of users
    public ResponseEntity<?> getUsers() throws Exception
    {
        return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK);
    }

    @PatchMapping("movies")  // http method=patch with url users - update a user with matching id
    public ResponseEntity<?> updateMovie(@RequestBody User user) throws UserNotFoundException,Exception
    {
        //Global exception handling- one example
        responseEntity=new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("user/{id}")   // http method=delete with url user/id - delete user by id
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") int id) throws UserNotFoundException, Exception
    {
        try{
            responseEntity=new ResponseEntity<User>(userService.deleteUser(id), HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
