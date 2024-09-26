package com.journal.journalApp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
public class configJournalAppEntity {

    private String key;
    private String value;
}
