package persnal.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import persnal.journalApp.entity.User;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
}