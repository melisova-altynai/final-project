package com.example.finalproject1.services;

import com.example.finalproject1.dto.UserDTO;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.exceptions.NotFoundException;
import com.example.finalproject1.mappers.UserMapper;
import com.example.finalproject1.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private final UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepo userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOtoUser(userDTO);
        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public Optional<UserDTO> getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(userMapper::userToUserDTO);
    }

    @Override
    @Transactional
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println(users.get(0).getOrganizedEvents());
        return users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(int id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User updatedUser = userMapper.userDTOtoUser(userDTO);
            updatedUser.setId(id);
            userRepository.save(updatedUser);
        }
        else{
            throw new NotFoundException("User with id " + id + " not found");

        }
    }
}
