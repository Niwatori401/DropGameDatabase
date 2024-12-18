package party.niwatori.dropgame_scoreboard.controller;

import java.security.MessageDigest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import party.niwatori.dropgame_scoreboard.data.Score;
import party.niwatori.dropgame_scoreboard.data.ScoreWithValidation;
import party.niwatori.dropgame_scoreboard.service.ScoreService;
import party.niwatori.dropgame_scoreboard.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

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
     * curl -X POST -H "Content-Type: application/json" -d '{"passhash":"FWADD", "userId": {"userName":"John Doe"}, "score":123}' localhost:8080/score/newScore
     */
    @PostMapping("/newScore")
    public ResponseEntity<Score> addNewScore(@RequestBody ScoreWithValidation score, HttpServletRequest request) {

        
        if (!userService.userNameExists(score.getUserId().getUserName())) {
            return ResponseEntity.badRequest().build();
        }
        if (this.userService.getUserByUserNamePasshash(score.getUserId().getUserName(), score.getPasshash()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Score newScore = new Score();
        newScore.setIpHash(hashIpAddress(extractClientIp(request)));
        newScore.setScore(score.getScore());
        newScore.setUserId(score.getUserId());

        Score s = scoreService.saveScore(newScore);

        return ResponseEntity.created(null).body(s);
    }

    private String extractClientIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Forwarded-For");
        if (remoteAddr == null || remoteAddr.isEmpty()) {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;
    }

    private String hashIpAddress(String ipAddress) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(ipAddress.getBytes("UTF-8"));
            
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuffer.append(String.format("%02x", b));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not hash IP address", e);
        }
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
