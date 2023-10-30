package dev.sugaroflead.dropgame_scoreboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import dev.sugaroflead.dropgame_scoreboard.data.NamePasshashCompositeKey;
import dev.sugaroflead.dropgame_scoreboard.data.User;


public interface UserRepository extends CrudRepository<User, NamePasshashCompositeKey> {
    @Query(value="SELECT user_name FROM users WHERE user_name = :userName", nativeQuery = true)
    public Optional<String> getUserName(@Param("userName") String userName);
}
