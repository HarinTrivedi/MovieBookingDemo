package com.demo.customer.service;

import com.demo.customer.data.User;
import com.demo.customer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User save(User user) {
        user.setPassword(Base64.getEncoder().encodeToString((user.getName()+user.getPhoneNumber()).getBytes()));
        return repository.save(user);
    }

    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    public Optional<User> findUser(String phone, String password){
        return repository.findByPhoneNumberAndPassword(phone, password);
    }
}
