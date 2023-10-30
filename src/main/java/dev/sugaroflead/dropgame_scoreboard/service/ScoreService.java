package dev.sugaroflead.dropgame_scoreboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sugaroflead.dropgame_scoreboard.repository.ScoreRepository;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
}
