package network.repository;

import network.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    @Query(value = "select * from user_info where id = :id limit 1", nativeQuery = true)
    Optional<UserInfo> findById(@Param("id") String id);

    @Query(value = "select * " +
            "from user_info " +
            "where  first_name like :firstName||'%' and second_name like :secondName||'%' order by id", nativeQuery = true)
    List<UserInfo> findByFirstNameAndSecondName(@Param("firstName") String firstName, @Param("secondName") String secondName);
}
