package persnal.journalApp.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import persnal.journalApp.entity.JournalEntry;
import persnal.journalApp.entity.User;
import persnal.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    public JournalEntry saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            if (user == null) {
                throw new RuntimeException("User not found with username: " + userName);
            }

            journalEntry.setUser(user);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);

            if (user.getJournalEntries() == null) {
                user.setJournalEntries(new ArrayList<>());
            }
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
            return saved;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred saving this entry " + e);
        }
    }

    public JournalEntry saveEntry(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllJournalEntryByUser(User user) {
        return journalEntryRepository.findByUser(user);
    }

    public Optional<JournalEntry> findId(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}



  




