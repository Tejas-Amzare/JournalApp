package persnal.journalApp.services;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persnal.journalApp.entity.User;
import persnal.journalApp.repository.UserEntryRepository;

@Service
public class UserService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public User save(User user) {
        return userEntryRepository.save(user);
    }

    public List<User> getAll() {
        return userEntryRepository.findAll();
    }

    public void findId(ObjectId id) {
        userEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id) {
        if (userEntryRepository.existsById(id)) {
            userEntryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User findByUserName(String username) {
        return userEntryRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userEntryRepository.findAll();
    }

    public void saveEntry(User user) {
        userEntryRepository.save(user);
    }
}
