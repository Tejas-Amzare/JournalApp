package persnal.journalApp.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import persnal.journalApp.entity.JournalEntry;
import persnal.journalApp.entity.User;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    List<JournalEntry> findByUser(User user);
}