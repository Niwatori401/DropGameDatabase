package dev.sugaroflead.dropgame_scoreboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sugaroflead.dropgame_scoreboard.data.Score;
import dev.sugaroflead.dropgame_scoreboard.repository.ScoreRepository;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    public List<Score> getUserScores(String userName) {
        return this.scoreRepository.getAllScoresByUserName(userName);
    }
}
