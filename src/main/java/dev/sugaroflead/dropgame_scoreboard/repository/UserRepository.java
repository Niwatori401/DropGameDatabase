package dev.sugaroflead.dropgame_scoreboard.repository;

import org.springframework.data.repository.CrudRepository;

import dev.sugaroflead.dropgame_scoreboard.data.NamePasshashCompositeKey;
import dev.sugaroflead.dropgame_scoreboard.data.User;


public interface UserRepository extends CrudRepository<User, NamePasshashCompositeKey> {
    
}
