package com.example.rentease.service.impl;

import com.example.rentease.dto.UserDTO;
import com.example.rentease.model.User;
import com.example.rentease.repository.UserRepository;
import com.example.rentease.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDTO);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the password here
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = convertToEntity(userDTO);
            user.setId(id);
            // Only encode if a new password is provided
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            User updatedUser = userRepository.save(user);
            return convertToDTO(updatedUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }
}