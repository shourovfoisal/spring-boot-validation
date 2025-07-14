package com.shourov.validation_example.service;

import com.shourov.validation_example.dto.UserRequest;
import com.shourov.validation_example.entity.User;
import com.shourov.validation_example.exception.UserNotFoundException;
import com.shourov.validation_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User addUser(UserRequest request) {
        User user = User.build(
                0,
                request.getAge(),
                request.getName(),
                request.getEmail(),
                request.getMobile(),
                request.getGender(),
                request.getNationality()
        );
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) throws UserNotFoundException {

        User user = repository.findByUserId(id);
        if(user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }
}
