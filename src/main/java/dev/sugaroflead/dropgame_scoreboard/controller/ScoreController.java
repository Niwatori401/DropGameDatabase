package dev.sugaroflead.dropgame_scoreboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sugaroflead.dropgame_scoreboard.data.Score;
import dev.sugaroflead.dropgame_scoreboard.service.ScoreService;
import dev.sugaroflead.dropgame_scoreboard.service.UserService;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private final ScoreService scoreService;

    @Autowired
    private final UserService userService;

    public ScoreController(ScoreService scoreService, UserService userService) {
        this.scoreService = scoreService;
        this.userService = userService;
    }

    /*
     * Example POST:
     * curl -X POST -H "Content-Type: application/json" -d '{"userId": {"userName":"John Doe"}, "ipHash":"123AED", "score":123}' localhost:8080/score/newScore
     */
    @PostMapping("/newScore")
    public ResponseEntity<Score> addNewScore(@RequestBody Score score) {

        if (!userService.userNameExists(score.getUserId().getUserName())) {
            return ResponseEntity.badRequest().build();
        }

        Score s = scoreService.saveScore(score);

        return ResponseEntity.created(null).body(s);
    }


    /*
     * Example GET:
     * curl -X GET http://localhost:8080/score/userScores/John%20Doe
     */
    @GetMapping("/userScores/{userName}")
    public ResponseEntity<List<Score>> getUserScores(@PathVariable String userName) {
        List<Score> scores = scoreService.getUserScores(userName);
        if(scores.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(scores);
        }
    }


}
