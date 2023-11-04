package dev.sugaroflead.dropgame_scoreboard.data;

import lombok.Data;

@Data
public class UserNameSubmitResponse {
    Boolean nameOk;
    String nameReason;
    Boolean passOk;
    String passReason;
}
