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

    // Save journal entry (linked to user)
      public JournalEntry saveEntry(JournalEntry journalEntry, String userName) {
        try {
            List<User> users = userService.findByUserName(userName); // changed to handle multiple matches
            if (users.isEmpty()) {
                throw new RuntimeException("User not found with username: " + userName);
            }
            User user = users.get(0); // take the first match

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
            throw new RuntimeException("An error occurred saving this entry: " + e.getMessage());
        }
    }

    // Save journal entry without user (used during update)
    public JournalEntry saveEntry(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

    // Find all entries
    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    // Get all entries by user
    public List<JournalEntry> getAllJournalEntryByUser(User user) {
        return journalEntryRepository.findByUser(user);
    }

    // get by id
    public Optional<JournalEntry> findId(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    /// Delete journal entry by ID and user
    public boolean deleteById(ObjectId id, String userName) {
        Optional<JournalEntry> journal = journalEntryRepository.findById(id);
        if (journal.isEmpty()) return false;

        List<User> users = userService.findByUserName(userName); // changed to list
        if (users.isEmpty()) return false;
        User user = users.get(0);

        if (user.getJournalEntries() == null) return false;

        boolean removed = user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        if (!removed) return false;

        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
        return true;
    }
}






