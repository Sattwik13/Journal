package com.journal.journalApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class userRepositoryImplTests {

    @Autowired
    private userRepositoryImpl UserRepository;

    @Test
    public void testSaveNewUser(){
      UserRepository.getUserForSentimentAnalysis();

    }
}
