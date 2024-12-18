package party.niwatori.dropgame_scoreboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import party.niwatori.dropgame_scoreboard.data.Score;
import party.niwatori.dropgame_scoreboard.repository.ScoreRepository;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;

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
