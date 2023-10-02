package network.repository;

import network.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, String> {
    @Query(value = "select post_id " +
            "from feed " +
            "where user_id =:userId " +
            "order by create_datetime desc limit :limit", nativeQuery = true)
    List<String> findAllForUser(@Param("userId") String userId, @Param("limit") BigInteger limit);

    @Modifying
    @Query(value = "delete from feed where post_id = :postId", nativeQuery = true)
    void deletePosts(@Param("postId") String postId);

    @Modifying
    @Query(value = "insert into feed(user_id, post_id) " +
            "select user_id, :postId from friend where friend_id = :friendId", nativeQuery = true)
    void addPosts(@Param("friendId") String friendId, @Param("postId") String postId);
}
