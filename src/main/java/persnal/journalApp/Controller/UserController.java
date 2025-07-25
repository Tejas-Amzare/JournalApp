package persnal.journalApp.Controller;

import java.util.List;
import persnal.journalApp.entity.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persnal.journalApp.services.UserService;

@Tag(name = "User Controller", description = "User management APIs")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(summary = "Get all users", description = "Return a list of all registered users")
    @ApiResponse(responseCode = "200", description = "User fetched sucessfully")
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    @Operation(summary = "Create a new user", description = "Registers a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user input")
    })
    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @Operation(summary = "Update user details", description = "Update username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username) {
        List<User> users = userService.findByUserName(username);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User userIndb = users.get(0);
        userIndb.setUsername(user.getUsername());
        userIndb.setPassword(user.getPassword());
        userService.save(userIndb);

        return ResponseEntity.ok(userIndb);
    }

    
    @Operation(summary = "Delete a user", description = "Deletes user and all related journal entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedUserId(@PathVariable String id) {
        boolean delete = userService.deleteById(new ObjectId(id));
        if (delete) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}