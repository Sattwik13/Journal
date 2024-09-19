package com.journal.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.journal.journalApp.entity.userEntry;
import com.journal.journalApp.repository.userRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private userRepository UserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        userEntry user = UserRepository.findByUserName(username);
        if(user != null){
            UserDetails userDetails = User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();
            return userDetails;    
        }
        throw new UsernameNotFoundException("User not found with username" + username);
    }

}
