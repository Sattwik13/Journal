package com.journal.journalApp.controller;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.journal.journalApp.Service.journalEntryService;
import com.journal.journalApp.Service.userService;
import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.entity.userEntry;




@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    private journalEntryService JournalEntryService;

    @Autowired
    private userService Userservice;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {

      userEntry User = Userservice.findByUserName(userName);  
      List<journalEntry> all = User.getJournalEntries();

      if(all != null && !all.isEmpty()) {
        return new ResponseEntity(all, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("{userName}")
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry, @PathVariable String userName){
       try {
           
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
       
        // return JournalEntryService.findById(myId).orElse(null);
        Optional<journalEntry> JournalEntry = JournalEntryService.findById(myId);
        if(JournalEntry.isPresent()) {
            return new ResponseEntity<>(JournalEntry.get(), HttpStatus.OK);
            
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
        }
    }    


    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<journalEntry> deleteJournalEntry(@PathVariable ObjectId myId, @PathVariable String userName){
       JournalEntryService.deleteById(myId, userName);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("id/{userName}/{id}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id, @RequestBody journalEntry newEntry, @PathVariable String userName){
    
    // Find the existing journal entry
    journalEntry oldEntry = JournalEntryService.findById(id).orElse(null);
    
    // Check if the entry exists
    if (oldEntry == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If not found, return 404
    }

    try {
        // Update fields if they are not null or empty
        oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
        oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());

        // Set date - either the provided one or the current time if null
        oldEntry.setDate(newEntry.getDate() != null ? newEntry.getDate() : LocalDateTime.now());

        // Save updated entry
        JournalEntryService.saveEntry(oldEntry);

        // Return the updated entry with 200 OK status
        return new ResponseEntity<>(oldEntry, HttpStatus.OK);

    } catch (Exception e) {
        // Handle any exceptions during the update process
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on error
    }
}

}
