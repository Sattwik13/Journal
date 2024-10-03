package com.journal.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.journal.journalApp.model.sentimentData;

@Service
public class sentimentConsumerService {

    @Autowired
    private emailService EmailService;

    @KafkaListener(topics = "weekly-sentiments", groupId = "weekly-sentiment-group")
    public void consume(sentimentData SentimentData) {
        sendEmail(SentimentData);
    }

    private void sendEmail(sentimentData SentimentData) {
        EmailService.sendEmail(SentimentData.getEmail(), "Sentiment for previous week", SentimentData.getSentiment());
    }
}
