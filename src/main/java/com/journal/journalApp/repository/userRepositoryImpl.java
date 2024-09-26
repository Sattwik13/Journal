package com.journal.journalApp.repository;
import com.journal.journalApp.entity.userEntry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;



public class userRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<userEntry> getUserForSentimentAnalysis() {

        Query query = new Query();
        // query.addCriteria(Criteria.where("name").is("Ram"));
        // query.addCriteria(Criteria.where("email").ne(null).ne(""));
        // query.addCriteria(Criteria.where("roles").in("USER","ADMIN"));
        // query.addCriteria(Criteria.where("email").exists(true));
        // query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        // --------> Using OR Operator
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),Criteria.where("sentimentAnalysis").is(true)));

        List<userEntry> users = mongoTemplate.find(query, userEntry.class);
        return users;
    }

}
