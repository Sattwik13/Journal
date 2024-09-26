package com.journal.journalApp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journal.journalApp.entity.configJournalAppEntity;
import com.journal.journalApp.repository.configJournalAppRepository;

@Component
public class appCache {

    public enum keys{

        WEATHER_API;
    }

    @Autowired
    private configJournalAppRepository ConfigJournalAppRepository;

    public Map<String, String> AppCache = new HashMap<>();

    @PostConstruct
    public void init() {
        List<configJournalAppEntity> all = ConfigJournalAppRepository.findAll();
        for(configJournalAppEntity ConfigJournalAppEntity : all){
            AppCache.put(ConfigJournalAppEntity.getKey(), ConfigJournalAppEntity.getValue());
        }
        
    }
}

