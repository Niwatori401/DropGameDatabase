package party.niwatori.dropgame_scoreboard.data;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class NamePasshashCompositeKey implements Serializable {
    private String userName;
    private String passHash;
}
