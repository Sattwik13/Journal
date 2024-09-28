package com.journal.journalApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journal.journalApp.schedulers.userScheduler;

@SpringBootTest
public class UserSchedulersTest {

    @Autowired
    private userScheduler UserScheduler;

    @Test
    public void testFetchUsersAndSendSEMail() {
        UserScheduler.fetchUsersAndSendSEMail();
    }
}
