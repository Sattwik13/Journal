package com.journal.journalApp.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.journal.journalApp.Service.emailService;
import com.journal.journalApp.Service.sentimentAnalysisService;
import com.journal.journalApp.cache.appCache;
import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.entity.userEntry;
import com.journal.journalApp.enums.sentiment;
import com.journal.journalApp.model.sentimentData;
import com.journal.journalApp.repository.userRepositoryImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
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

    @Autowired
    private KafkaTemplate<String, sentimentData> kafkaTemplate;

    // @Scheduled(cron = "0 * *  ? *  *")
    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSEMail() {

        List<userEntry> users = UserRepositoryImpl.getUserForSentimentAnalysis();
        for(userEntry user : users){

            List<journalEntry> JournalEntries = user.getJournalEntries();
            List<sentiment> sentiments = JournalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<sentiment, Integer> sentimentCounts = new HashMap<>();
            for(sentiment  Sentiment : sentiments) {
                if(Sentiment != null) {
                    sentimentCounts.put(Sentiment, sentimentCounts.getOrDefault(Sentiment, 0) + 1);
                }
            }
            sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<sentiment, Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue() > maxCount) {
                    maxCount =  entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                } 
            }
            if (mostFrequentSentiment != null) {
                sentimentData  SentimentData= sentimentData.builder().email(user.getEmail()).sentiment( "Sentiment for last 7 days" + mostFrequentSentiment.toString()).build();
                try {
                    kafkaTemplate.send("weekly-sentiments", SentimentData.getEmail(), SentimentData);
                } catch (Exception e) {
                    EmailService.sendEmail(SentimentData.getEmail(), "sentiment for previous week", SentimentData.getSentiment());
                }
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        Appcache.init();
    }
}
