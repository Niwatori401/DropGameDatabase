package dev.sugaroflead.dropgame_scoreboard.repository;

import dev.sugaroflead.dropgame_scoreboard.data.Score;
import dev.sugaroflead.dropgame_scoreboard.data.IdDateCompositeKey;

import org.springframework.data.repository.CrudRepository;


public interface ScoreRepository extends CrudRepository<Score, IdDateCompositeKey>{
    
}
