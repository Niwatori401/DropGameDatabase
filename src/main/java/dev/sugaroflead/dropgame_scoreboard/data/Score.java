package dev.sugaroflead.dropgame_scoreboard.data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Score {
    @EmbeddedId
    private UsernameDateCompositeKey userId;
    private String ipHash;
    private Integer score;

    @PrePersist
    public void prePersist() {
        if (userId.getCreatedDate() == null) {
            userId.setCreatedDate(LocalDateTime.now());
        }
    }


    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
