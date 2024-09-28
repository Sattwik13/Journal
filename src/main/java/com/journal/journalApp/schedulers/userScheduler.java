package com.journal.journalApp.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.journal.journalApp.Service.emailService;
import com.journal.journalApp.Service.sentimentAnalysisService;
import com.journal.journalApp.cache.appCache;
import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.entity.userEntry;
import com.journal.journalApp.repository.userRepositoryImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class userScheduler {


    @Autowired
    private emailService EmailService;

    @Autowired
    private userRepositoryImpl UserRepositoryImpl;

    @Autowired
    private sentimentAnalysisService SentimentAnalysisService;

    @Autowired
    private appCache Appcache;

    // @Scheduled(cron = "0 * *  ? *  *")
    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSEMail() {

        List<userEntry> users = UserRepositoryImpl.getUserForSentimentAnalysis();
        for(userEntry user : users){

            List<journalEntry> JournalEntries = user.getJournalEntries();
            List<String> filteredEntries = JournalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join("", filteredEntries);
            String sentiment = SentimentAnalysisService.getSentiment(entry);
            // EmailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        Appcache.init();
    }
}
