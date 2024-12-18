package party.niwatori.dropgame_scoreboard.data;

import lombok.Data;

@Data
public class ScoreWithValidation extends Score {
    String passhash;
}
