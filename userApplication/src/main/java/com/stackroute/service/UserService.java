package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsExecption;
import com.stackroute.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    // save the new user
    public User saveUser(User user) throws UserAlreadyExistsExecption;

    //get the list of users
    public List<User>  getAllUser();

    //to update the user
    public User updateUser(User user)throws UserNotFoundException;

    //to delete the user with given id
    public User deleteUser(int id) throws UserNotFoundException;

}
