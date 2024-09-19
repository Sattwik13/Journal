package com.journal.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.journal.journalApp.entity.userEntry;

@Repository
public interface userRepository extends MongoRepository<userEntry, ObjectId> {

    userEntry findByUserName(String username);

    void deleteByUserName(String username);

}
