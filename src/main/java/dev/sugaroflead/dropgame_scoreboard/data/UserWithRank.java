package dev.sugaroflead.dropgame_scoreboard.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithRank extends User {
    public Integer rank = -1;
}
