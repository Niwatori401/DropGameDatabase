package dev.sugaroflead.dropgame_scoreboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sugaroflead.dropgame_scoreboard.data.User;
import dev.sugaroflead.dropgame_scoreboard.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    /*
     * Expects a POST request like this:
     * curl -X POST -H "Content-Type: application/json" -d '{"namePassHash": {"userName": "John Doe", "passHash": "123456"}}' http://localhost:8080/user/createUser
     */
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /*
     * Expects a GET request like this:
     * curl -X GET {url}/user/getUser/JohnDoe/1321AE
     */
    @GetMapping("getUser/{userName}/{passhash}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String userName, @PathVariable String passhash) {
        return userService.getUserByUserNamePasshash(userName, passhash)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
