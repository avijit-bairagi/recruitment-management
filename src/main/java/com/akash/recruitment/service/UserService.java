package com.akash.recruitment.service;


import com.akash.recruitment.entity.UserEntity;
import com.akash.recruitment.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            user = userRepository.findByUserName(email);
            if (user == null) {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
        }

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
