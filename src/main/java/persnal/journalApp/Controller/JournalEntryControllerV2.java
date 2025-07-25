package persnal.journalApp.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import persnal.journalApp.entity.JournalEntry;
import persnal.journalApp.entity.User;
import persnal.journalApp.services.JournalEntryServices;
import persnal.journalApp.services.UserService;

@Tag(name = "Journal Controller", description = "Journal Entry management APIs")
@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @Autowired
    private UserService userService;

    // Get all journal entries by a specific user
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntryByUser(@PathVariable String userName) {
        List<User> user = userService.findByUserName(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        List<JournalEntry> entries = journalEntryServices.getAllJournalEntryByUser(user.get(0));
        if (entries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No entries found");
        }

        return ResponseEntity.ok(entries);
    }

    // Create a journal entry for a user
    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry, @PathVariable String userName) {
        try {
            JournalEntry saved = journalEntryServices.saveEntry(entry, userName);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    // Get all journal entries (admin or test use)
    @GetMapping("/getAll")
    public ResponseEntity<List<JournalEntry>> getAll() {
        return ResponseEntity.ok(journalEntryServices.findAll());
    }

    // Get a journal entry by its ObjectId
    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable String myId) {
        try {
            Optional<JournalEntry> journalEntry = journalEntryServices.findId(new ObjectId(myId));
            return journalEntry
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal entry not found"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        }
    }

    // Delete a journal entry by its ID and username
    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String myId, @PathVariable String userName) {
        try {
            boolean deleted = journalEntryServices.deleteById(new ObjectId(myId), userName);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found or not owned by user");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        }
    }

    // Update journal entry by ID
    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable String id,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName) {
        try {
            Optional<JournalEntry> optionalOldEntry = journalEntryServices.findId(new ObjectId(id));
            if (optionalOldEntry.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal entry not found");
            }

            JournalEntry old = optionalOldEntry.get();
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isBlank() ? newEntry.getTitle()
                    : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isBlank() ? newEntry.getContent()
                    : old.getContent());
            JournalEntry updated = journalEntryServices.saveEntry(old);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        }
    }
}
