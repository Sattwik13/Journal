package com.journal.journalApp.Service;

import java.util.Arrays;
// import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

// import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.entity.userEntry;
// import com.journal.journalApp.repository.journalEntryRepository;
import com.journal.journalApp.repository.userRepository;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class userService {

    @Autowired
    private userRepository UserRepository;

    // @Autowired
    // private static final Logger logger = LoggerFactory.getLogger(userService.class); // @slf4j annotation use substitute of this line

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(userEntry user) {
        // journalEntry.setData(LocalDateTime.now());
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            UserRepository.save(user);
        } catch (Exception e) {
            // logger.info("hahahahaha");
            // logger.error("Error occured", e);
            // logger.error("Error occured {}",user.getUserName(), e);
            log.error("Error occured {}",user.getUserName(), e); // --> when slf4j annotaion use then log instance create, that's reason use log
            log.debug("hahahahaha");


        }
    }

    public void saveAdmin(userEntry user) {
        // journalEntry.setData(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        UserRepository.save(user);
    }

    public void saveUser(userEntry user) {
       UserRepository.save(user);
    }

    public List<userEntry> getAll(){
        return UserRepository.findAll();
    }

    public Optional<userEntry> findById(ObjectId id) {
        return UserRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        UserRepository.deleteById(id);
    }

    public userEntry findByUserName(String username){
        return UserRepository.findByUserName(username);
    }
}
