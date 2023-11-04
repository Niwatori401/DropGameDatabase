package dev.sugaroflead.dropgame_scoreboard.data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="users") // User is a reserved keyword in SQL
public class User {
    @EmbeddedId
    private NamePasshashCompositeKey namePassHash;
    
    private Integer topScore = 0;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
