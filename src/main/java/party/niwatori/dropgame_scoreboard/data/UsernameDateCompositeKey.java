package party.niwatori.dropgame_scoreboard.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class UsernameDateCompositeKey implements Serializable {
    private String userName;

    private LocalDateTime createdDate;
}
