package dev.sugaroflead.dropgame_scoreboard.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sugaroflead.dropgame_scoreboard.data.NamePasshashCompositeKey;
import dev.sugaroflead.dropgame_scoreboard.data.User;
import dev.sugaroflead.dropgame_scoreboard.data.UserNameSubmitResponse;
import dev.sugaroflead.dropgame_scoreboard.data.UserWithRank;
import dev.sugaroflead.dropgame_scoreboard.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    Pattern allowablePattern = Pattern.compile("^[a-zA-Z0-9_-]{3,}$");


    /*
     * Expects a POST request like this:
     * curl -X POST -H "Content-Type: application/json" -d '{"namePassHash": {"userName": "John_Doe", "passHash": "123456"}}' http://localhost:8080/user/createUser
     */
    @PostMapping("/createUser")
    public ResponseEntity<UserNameSubmitResponse> createUser(@RequestBody User user) {
        UserNameSubmitResponse response = new UserNameSubmitResponse();
        
        if (this.userService.userNameExists(user.getNamePassHash().getUserName())) {
            Optional<User> existingUser = this.userService.getUserByUserNamePasshash(user.getNamePassHash().getUserName(), user.getNamePassHash().getPassHash());
            if (existingUser.isEmpty()) {
                response.setNameOk(false);
                response.setNameReason("Name already in use.");
            }
            else {
                response.setNameOk(true);
                response.setPassOk(true);
                return ResponseEntity.ok().body(response);
            }
            
        }
        else if(!allowablePattern.matcher(user.getNamePassHash().getUserName()).matches()) {
            response.setNameOk(false);
            response.setNameReason("Name must only contain alphanumeric characters, underscore and the hypen and be at least 3 characters long.");
        }
        else {
            response.setNameOk(true);
        }
        
        if(!allowablePattern.matcher(user.getNamePassHash().getPassHash()).matches()) {
            response.setPassOk(false);
            response.setPassReason("Password hash contained illegal characters.");
        }
        else {
            response.setPassOk(true);
        }
        
        if (response.getNameOk() && response.getPassOk()) {
            userService.saveUser(user);

            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body(response);
    }
    
    @GetMapping("getLeaderboard/{n}/{m}")
    public ResponseEntity<List<UserWithRank>> getN_UsersStartingFrom_M(@PathVariable Integer n, @PathVariable Integer m) {
        
        List<UserWithRank> users = userService.getN_UsersStartingFrom_M(n, m);
        
        if (users == null || users.size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        else {
            for (int i = 0; i < users.size(); i++) {
                NamePasshashCompositeKey namepass = new NamePasshashCompositeKey();
                namepass.setUserName(users.get(i).getNamePassHash().getUserName());
                namepass.setPassHash("-");
                users.get(i).setNamePassHash(namepass);
            }

            return ResponseEntity.ok().body(users);
        }
    }

    @GetMapping("getUserCount")
    public ResponseEntity<Integer> getUserCount() {
        return ResponseEntity.ok().body(this.userService.getUserCount());
    }


    @GetMapping("getUser/{userName}/rank") 
    public ResponseEntity<UserWithRank> getUserWithRankByUsername(@PathVariable String userName) {
        UserWithRank user = this.userService.getUserWithRankByUsername(userName);

        if (user == null)
            return ResponseEntity.badRequest().build();
        
        NamePasshashCompositeKey namepass = new NamePasshashCompositeKey();
        namepass.setUserName(userName);
        namepass.setPassHash("-");
        user.setNamePassHash(namepass);

        return ResponseEntity.ok().body(user);
    }
}
