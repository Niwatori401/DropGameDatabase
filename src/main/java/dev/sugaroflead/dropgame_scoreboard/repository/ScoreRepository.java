package dev.sugaroflead.dropgame_scoreboard.repository;

import dev.sugaroflead.dropgame_scoreboard.data.Score;
import dev.sugaroflead.dropgame_scoreboard.data.UsernameDateCompositeKey;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ScoreRepository extends CrudRepository<Score, UsernameDateCompositeKey>{
    @Query(value="SELECT * FROM score WHERE user_name = :userName", nativeQuery = true)
    public List<Score> getAllScoresByUserName(@Param("userName") String userName);
}
