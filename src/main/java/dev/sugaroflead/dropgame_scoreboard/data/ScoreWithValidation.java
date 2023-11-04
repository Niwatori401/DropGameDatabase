package dev.sugaroflead.dropgame_scoreboard.data;

import lombok.Data;

@Data
public class ScoreWithValidation extends Score {
    String passhash;
}
