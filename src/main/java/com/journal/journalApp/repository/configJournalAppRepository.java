package com.journal.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.journal.journalApp.entity.configJournalAppEntity;

@Repository
public interface configJournalAppRepository extends MongoRepository<configJournalAppEntity, ObjectId> {

   
}

// Controller  ---> service ---> repositry