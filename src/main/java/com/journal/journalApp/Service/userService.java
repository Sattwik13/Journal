package com.journal.journalApp.Service;

// import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.entity.userEntry;
// import com.journal.journalApp.repository.journalEntryRepository;
import com.journal.journalApp.repository.userRepository;


@Controller
public class userService {

    @Autowired
    private userRepository UserRepository;

    public void saveEntry(userEntry JournalEntry) {
        // journalEntry.setData(LocalDateTime.now());
        UserRepository.save(JournalEntry);
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
