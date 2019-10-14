package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsExecption;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsExecption {
        if(userRepository.existsById(user.getId()))     // if user  exists with that id throw exception
        {
            throw new UserAlreadyExistsExecption("User already exists");
        }
        User saveUser=userRepository.save(user);      //save user
        return saveUser;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();   //get all the users
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        if(!userRepository.existsById(user.getId()))    // if no user exists with that id throw exception
        {
            throw new UserNotFoundException("User Not Found");
        }
        User updateUser=userRepository.findById(user.getId()).get();  //get user by id
        if(user.getFirstName()!=null)
            updateUser.setFirstName(user.getLastName());
        if(user.getLastName()!=null)
            updateUser.setLastName(user.getLastName());
        return updateUser;
    }

    @Override
    public User deleteUser(int id) throws UserNotFoundException {
        if(!userRepository.existsById(id))     // if no user exists with that id throw exception
        {
            throw new UserNotFoundException("User Not Found");
        }
        User deleteUser=userRepository.findById(id).get();    //get user by id
        userRepository.deleteById(id);  //delete by id
        return deleteUser;
    }
}
