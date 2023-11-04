package dev.sugaroflead.dropgame_scoreboard.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.sugaroflead.dropgame_scoreboard.data.NamePasshashCompositeKey;
import dev.sugaroflead.dropgame_scoreboard.data.User;
import dev.sugaroflead.dropgame_scoreboard.data.UserWithRank;
import dev.sugaroflead.dropgame_scoreboard.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUserNamePasshash(String username, String passhash) {
        NamePasshashCompositeKey npck = new NamePasshashCompositeKey();
        npck.setPassHash(passhash);
        npck.setUserName(username);
        return userRepository.findById(npck);
    }

    public Boolean userNameExists(String username) {
        return this.userRepository.getUserName(username).isPresent();
    }

    public List<UserWithRank> getN_UsersStartingFrom_M(Integer n, Integer m) {
        List<Object[]> objectList = this.userRepository.getN_UsersStartingFrom_M(n, m);
        List<UserWithRank> userWithRanks = new ArrayList<>();

        for (Object[] data : objectList) {
            UserWithRank u = new UserWithRank();
            NamePasshashCompositeKey nph = new NamePasshashCompositeKey();
            u.setRank(((Long) data[0]).intValue());
            nph.setUserName((String) data[1]);
            nph.setPassHash((String) data[2]);
            u.setNamePassHash(nph);
            u.setTopScore((Integer) data[3]);
            u.setCreatedDate(((Timestamp) data[4]).toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
            u.setLastModifiedDate(((Timestamp) data[5]).toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
            
            userWithRanks.add(u);
        }

        return userWithRanks;
    }

    public UserWithRank getUserWithRankByUsername(String userName) {
        List<Object[]> dataCollection = this.userRepository.getUserWithRankByUsername(userName);
        if (dataCollection == null || dataCollection.size() == 0)
            return null;

        Object[] data = dataCollection.get(0);

        UserWithRank u = new UserWithRank();
        NamePasshashCompositeKey nph = new NamePasshashCompositeKey();
        
        u.setRank(((Long) data[0]).intValue());
        nph.setUserName((String) data[1]);
        nph.setPassHash((String) data[2]);
        u.setNamePassHash(nph);
        u.setTopScore((Integer) data[3]);
        u.setCreatedDate(((Timestamp) data[4]).toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
        u.setLastModifiedDate(((Timestamp) data[5]).toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
            
        return u;
    }

    public Integer getUserCount() {
        return this.userRepository.getUserCount();
    }
}
