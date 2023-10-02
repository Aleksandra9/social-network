package network.repository;

import network.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query(value = "select * " +
            "from friend " +
            "where user_id =:userId and friend_id = :friendId and delete_datetime is null", nativeQuery = true)
    Optional<Friend> findByUserIdAndFriendId(@Param("userId") String userId, @Param("friendId") String friendId);

    @Query(value = "select friend_id " +
            "from friend " +
            "where user_id =:userId and delete_datetime is null", nativeQuery = true)
    List<String> getFriends(@Param("userId") String userId);
}
