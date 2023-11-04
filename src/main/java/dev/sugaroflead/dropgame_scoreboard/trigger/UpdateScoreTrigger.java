package dev.sugaroflead.dropgame_scoreboard.trigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.h2.api.Trigger;

public class UpdateScoreTrigger implements Trigger {

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        String userName = newRow[4].toString();
        PreparedStatement updateStmt = conn.prepareStatement(
            "UPDATE USERS SET TOP_SCORE = (SELECT MAX(SCORE) FROM SCORE WHERE USER_NAME = ?) WHERE USER_NAME = ?"
        );
        updateStmt.setString(1, userName);
        updateStmt.setString(2, userName);
        updateStmt.execute();
    }
    
}
