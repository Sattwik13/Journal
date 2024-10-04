package com.journal.journalApp.controller;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.*;


import com.journal.journalApp.Service.journalEntryService;
import com.journal.journalApp.Service.userService;
import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.entity.userEntry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@RequestMapping("/journal")
@Tag(name = "Journal APIs")
public class journalEntryControllerV2 {

    @Autowired
    private journalEntryService JournalEntryService;

    @Autowired
    private userService Userservice;

   
    @GetMapping
    @Operation(summary = "Get all journal entries of a user")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String userName = authentication.getName();  
      userEntry User = Userservice.findByUserName(userName);  
      List<journalEntry> all = User.getJournalEntries();

      if(all != null && !all.isEmpty()) {
        return new ResponseEntity<>(all, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry){
       try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();   
        // userEntry User = Userservice.findByUserName(userName);
        myEntry.setDate(LocalDateTime.now());
        JournalEntryService.saveEntry(myEntry, userName);
        return new ResponseEntity<>(myEntry, HttpStatus.CREATED);

       } catch (Exception e) {
        
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }


    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryId(@PathVariable ObjectId myId){
       
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        // return JournalEntryService.findById(myId).orElse(null);
        userEntry user = Userservice.findByUserName(userName);
        List<journalEntry> collect = user.getJournalEntries()
                                         .stream()
                                         .filter(x -> x.getId()
                                         .equals(myId))
                                         .collect(Collectors.toList());
        if(!collect.isEmpty()){                                 
            Optional<journalEntry> JournalEntry = JournalEntryService.findById(myId);
            if(JournalEntry.isPresent()) {
            return new ResponseEntity<>(JournalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
    }    


    @DeleteMapping("id/{myId}")
    public ResponseEntity<journalEntry> deleteJournalEntry(@PathVariable ObjectId myId) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName(); 
       boolean removed = JournalEntryService.deleteById(myId, userName);
       if(removed){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }


    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id, @RequestBody journalEntry newEntry){
    
    // // Find the existing journal entry
    // journalEntry oldEntry = JournalEntryService.findById(id).orElse(null);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName(); 
    userEntry user = Userservice.findByUserName(userName);
    List<journalEntry> collect = user.getJournalEntries()
                                         .stream()
                                         .filter(x -> x.getId()
                                         .equals(id))
                                         .collect(Collectors.toList());
    if(!collect.isEmpty()){                                 
            Optional<journalEntry> JournalEntry = JournalEntryService.findById(id);
            if(JournalEntry.isPresent()) {
                journalEntry oldEntry = JournalEntry.get();
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());

                // Set date - either the provided one or the current time if null
                oldEntry.setDate(newEntry.getDate() != null ? newEntry.getDate() : LocalDateTime.now());

                // Save updated entry
                JournalEntryService.saveEntry(oldEntry);

                // Return the updated entry with 200 OK status
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            
            }
        }     
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);                                

    // Check if the entry exists
    

}

}
