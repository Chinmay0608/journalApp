package Chinmay.journalApp.Service;

import Chinmay.journalApp.entity.JournalEntry;
import Chinmay.journalApp.entity.User;
import Chinmay.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userEntryService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.saveUser(user);
        }catch (Exception e){
            log.error("Error :",e);
            throw new RuntimeException("An error has occurred while saving the entry.",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getALl(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userEntryService.findByUserName(userName);
            if (user != null) {
                removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
                if (removed) {
                    userEntryService.saveNewUser(user);
                    journalEntryRepository.deleteById(id);
                }
            }
        }catch (Exception e){
            log.error("Error :",e);
            throw new RuntimeException("An error has occurred while deleting the entry.");
        }
        return removed;
    }

    public List<JournalEntry> findByUserName(String userName){
        return Collections.emptyList();
    }

}
