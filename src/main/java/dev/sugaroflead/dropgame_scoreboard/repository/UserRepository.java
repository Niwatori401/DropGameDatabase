package dev.sugaroflead.dropgame_scoreboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import dev.sugaroflead.dropgame_scoreboard.data.NamePasshashCompositeKey;
import dev.sugaroflead.dropgame_scoreboard.data.User;


public interface UserRepository extends CrudRepository<User, NamePasshashCompositeKey> {
    @Query(value="SELECT user_name FROM users WHERE user_name = :userName", nativeQuery = true)
    public Optional<String> getUserName(@Param("userName") String userName);

    @Query(value="WITH ORDERED_SCORES AS (SELECT DISTINCT TOP_SCORE FROM USERS ORDER BY TOP_SCORE DESC), ORDERED_RANKS AS (SELECT ROW_NUMBER() OVER (ORDER BY TOP_SCORE DESC) AS RANK, TOP_SCORE FROM ORDERED_SCORES) SELECT RANK, USER_NAME, PASS_HASH, O.TOP_SCORE, CREATED_DATE, LAST_MODIFIED_DATE FROM USERS U LEFT JOIN ORDERED_RANKS O ON U.TOP_SCORE = O.TOP_SCORE ORDER BY RANK ASC LIMIT :nVar OFFSET :mVar", nativeQuery = true)
    public List<Object[]> getN_UsersStartingFrom_M(@Param("nVar") Integer n, @Param("mVar") Integer m);

}
