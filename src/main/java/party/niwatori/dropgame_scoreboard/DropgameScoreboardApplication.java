package party.niwatori.dropgame_scoreboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EnableJpaAuditing // Enables the use of @CreatedDate and @LastModifiedDate annotations
public class DropgameScoreboardApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DropgameScoreboardApplication.class, args);
        new CountDownLatch(1).await();
	}
}
