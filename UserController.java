package persnal.journalApp.Controller;

import java.util.List;
import persnal.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persnal.journalApp.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/username")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username) {
        User userIndb = userService.findByUserName(username);
        if (userIndb != null) {
            userIndb.setUsername(user.getUsername());
            userIndb.setPassword(user.getPassword());
            userService.save(userIndb);
        }
        return new ResponseEntity<>(userIndb, HttpStatus.NO_CONTENT);
    }
}