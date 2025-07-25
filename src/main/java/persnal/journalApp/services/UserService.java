package persnal.journalApp.services;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persnal.journalApp.entity.JournalEntry;
import persnal.journalApp.entity.User;
import persnal.journalApp.repository.JournalEntryRepository;
import persnal.journalApp.repository.UserEntryRepository;

@Service
public class UserService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    // save
    public User save(User user) {
        return userEntryRepository.save(user);
    }

    // get ALL user
    public List<User> getAll() {
        return userEntryRepository.findAll();
    }

    // find by Id
    public void findId(ObjectId id) {
        userEntryRepository.findById(id);
    }

    // delete user and their journal entry
    public boolean deleteById(ObjectId id) {
        if (userEntryRepository.existsById(id)) {
            User user = userEntryRepository.findById(id).orElse(null);

            if (user != null) {

                List<JournalEntry> journalEntries = journalEntryRepository.findByUser(user);
                journalEntryRepository.deleteAll(journalEntries);

                userEntryRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    // find user a username
    public List<User> findByUserName(String username) {
        return userEntryRepository.findByUsername(username);
    }

    // find all users
    public List<User> findAll() {
        return userEntryRepository.findAll();
    }

    // save update user
    public void saveEntry(User user) {
        userEntryRepository.save(user);
    }
}
