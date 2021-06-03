package com.dao;
import com.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    @Query(value = "from User where username = ?1 ")
    List<User> queryByName(String username);

    @Query(value = "select * from User.user where username like concat('%',?,'%')",nativeQuery = true)
    Page<User> QueryPage(@Param("username") String username, Pageable pageable);

    void deleteById(Integer id);

    @Override
    <S extends User> S save(S entity);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.username = ?1 ,u.email =?2, u.password= ?3 where u.id = ?4")
    void updateById(String username,String email,String password,Integer id);

    User queryByUsernameAndPassword(String username,String password);

    @Modifying
    @Transactional
    @Query(value = "update  User u set u.status = ?1 where u.id= ?2")
    int updateStatusById(int status,int id);

}

