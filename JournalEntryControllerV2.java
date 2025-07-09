package persnal.journalApp.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persnal.journalApp.entity.JournalEntry;
import persnal.journalApp.entity.User;
import persnal.journalApp.services.JournalEntryServices;
import persnal.journalApp.services.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntryByUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        List<JournalEntry> entries = journalEntryServices.getAllJournalEntryByUser(user);
        if (entries.isEmpty()) {
            return new ResponseEntity<>("No entries found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry, @PathVariable String userName) {
        try {
            JournalEntry saved = journalEntryServices.saveEntry(entry, userName);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryServices.findId(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
        journalEntryServices.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id,
                                                          @RequestBody JournalEntry newEntry,
                                                          @PathVariable String userName) {
        JournalEntry old = journalEntryServices.findId(id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryServices.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}