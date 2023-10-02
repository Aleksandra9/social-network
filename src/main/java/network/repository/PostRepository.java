package network.repository;

import network.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {
    @Query(value = "select * " +
            "from post " +
            "where id =:id and delete_datetime is null", nativeQuery = true)
    Optional<Post> findById(@Param("id") String id);
}
