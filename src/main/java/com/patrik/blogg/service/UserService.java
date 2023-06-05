package com.patrik.blogg.service;

import com.patrik.blogg.exception.UserNotFoundException;
import com.patrik.blogg.model.User;
import com.patrik.blogg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User newUser(User user){return userRepository.save(user);}

    public List<User> getAllUser(){return userRepository.findAll();}

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Long id, User editUser){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(editUser.getUserName());
                    user.setFirstName(editUser.getFirstName());
                    user.setLastName(editUser.getLastName());
                    user.setPassword(editUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }
}
