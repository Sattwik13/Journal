package com.journal.journalApp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.entity.userEntry;
import com.journal.journalApp.repository.journalEntryRepository;


@Controller
public class journalEntryService {

    @Autowired
    private journalEntryRepository JournalEntryRepository;

    @Autowired
    private userService UserService;

    @Transactional // 
    public void saveEntry(journalEntry JournalEntry, String userName) {

        try {
            userEntry User = UserService.findByUserName(userName); 
            JournalEntry.setDate(LocalDateTime.now());
            journalEntry saved = JournalEntryRepository.save(JournalEntry);
            User.getJournalEntries().add(saved);
            UserService.saveUser(User);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("An error occured while saving the entry");
        }
    }

    public void saveEntry(journalEntry JournalEntry) {

        JournalEntryRepository.save(JournalEntry);
    }    

    public List<journalEntry> getAll(){
        return JournalEntryRepository.findAll();
    }

    public Optional<journalEntry> findById(ObjectId id) {
        return JournalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            userEntry User = UserService.findByUserName(userName); 
            removed = User.getJournalEntries().removeIf(x-> x.getId().equals(id));
            if(removed){
               UserService.saveUser(User);
               JournalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println( "An error occured while deleteing the entry"+ e.getMessage());
           
        }
        return removed;
 
    }

    
}
