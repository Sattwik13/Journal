package com.journal.journalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users") // --> Document--convert mongoDb collection(users) 
@Data // ---> Using Lombok, For automatic (getter and setter) code generate on compile time 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class userEntry {

    @Id
    private ObjectId id;
    @Indexed(unique = true) // ---> for unique userName
    @NonNull
    private String userName;
    private String email;
    private boolean sentimentAnalysis;
    @NonNull
    private String password;

    @DBRef // --> Link, For Putting reference journalEntry in users collection
    private List<journalEntry> JournalEntries = new ArrayList<>();
    private List<String>  roles;

}
