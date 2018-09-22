package th.ac.kmutt.sit.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.kmutt.sit.Exception.ResourceNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.getAllUser();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/user/{id:[\\d]}",
            method = RequestMethod.GET
    )
    public Optional<User> getUser(@PathVariable("id") long userId) {
        return this.userService.findById(userId);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return this.userService.save(user);
    }

}