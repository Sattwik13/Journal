package com.journal.journalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class emailServiceTest {

    @Autowired
    private emailService EmailService;

    @Test
    void testSendMail() {
        EmailService.sendEmail("eloquentnobel1@imcourageous.com",
         "Testing Java mail sender", 
         "Hi, aap kaise hain?");
    }
}
