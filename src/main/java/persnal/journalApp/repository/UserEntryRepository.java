package persnal.journalApp.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import persnal.journalApp.entity.User;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
     // Fetch users by username (can return multiple results)
    List<User> findByUsername(String username);

    // Check if username exists
    boolean existsByUsername(String username);
}