package com.Service;

import com.pojo.User;
import com.pojo.Vo.UserVo;
import org.springframework.data.domain.Page;
import java.util.List;


public interface UserService {
    List<User> QueryByName(String username);

    Page<User> PageQuery(String username, Integer pageNum,Integer pageSize);

    void delete(Integer id);

    UserVo save(UserVo user);

    void update(UserVo userVo);

    UserVo queryByUsernameAndPassword(String username,String password);

    int updateStatusById(Integer status,Integer id);

}
