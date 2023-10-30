package dev.sugaroflead.dropgame_scoreboard.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class IdDateCompositeKey implements Serializable {
    private Integer userId;

    private LocalDateTime createdDate;
}
