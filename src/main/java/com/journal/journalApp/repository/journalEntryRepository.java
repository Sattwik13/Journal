package com.journal.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.journal.journalApp.entity.journalEntry;

@Repository
public interface journalEntryRepository extends MongoRepository<journalEntry, ObjectId> {

   
}

// Controller  ---> service ---> repositry