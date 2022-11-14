package com.manager.repairshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.manager.repairshop.entity.User;
import com.manager.repairshop.repository.UserRepository;

public class RepairshopUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserEmail(email);
        if (user != null) {
            return new RepairshopUserDetails(user);
        }
        throw new UsernameNotFoundException("Bad credentials");
    }
}
