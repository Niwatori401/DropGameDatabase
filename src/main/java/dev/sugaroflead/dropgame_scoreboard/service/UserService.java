package dev.sugaroflead.dropgame_scoreboard.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sugaroflead.dropgame_scoreboard.data.NamePasshashCompositeKey;
import dev.sugaroflead.dropgame_scoreboard.data.User;
import dev.sugaroflead.dropgame_scoreboard.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUserNamePasshash(String username, String passhash) {
        NamePasshashCompositeKey npck = new NamePasshashCompositeKey();
        npck.setPassHash(passhash);
        npck.setUserName(username);
        return userRepository.findById(npck);
    }

    public Boolean userNameExists(String username) {
        return this.userRepository.getUserName(username).isPresent();
    }
}
