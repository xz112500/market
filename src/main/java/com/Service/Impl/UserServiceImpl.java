package com.Service.Impl;

import com.Service.UserService;
import com.dao.UserDao;
import com.pojo.User;
import com.pojo.Vo.UserVo;
import com.util.JWTUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@EnableCaching
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public List<User> QueryByName(String username) {
      List<User> list=userDao.queryByName(username);
      return list.size() !=0 ? list : null ;
    }
    @Cacheable(value = "page",key = "#username")
    public Page<User> PageQuery( String username, Integer pageNum,Integer pageSize){
          Sort sort=Sort.by(Sort.Direction.ASC,"id");
          Pageable pageable=PageRequest.of(pageNum, pageSize, sort);
          return userDao.QueryPage(username,pageable);
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteById(id);
    }

    public UserVo save(UserVo user){
        String Md5Pass= DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        User user1=new User(user.getId(),user.getUsername(),Md5Pass,new JWTUtil().getJWT(user.getUsername(), Md5Pass), user.getEmail(),user.getStatus());
        userDao.save(user1);
        return new UserVo(user1.getId(),user1.getUsername(),user1.getEmail(),user1.getStatus(),user1.getToken());
    }

    public void update(UserVo userVo){
        userDao.updateById(userVo.getUsername(),userVo.getEmail(),userVo.getPassword(),userVo.getId());
    }
    public UserVo queryByUsernameAndPassword(String username,String password){
       User user= userDao.queryByUsernameAndPassword(username,password);
       return user != null ? new UserVo(user.getId(),user.getUsername(),user.getEmail(),user.getStatus(),user.getToken()) : null;
    }

    @Override
    public int updateStatusById(Integer status, Integer id) {
        return userDao.updateStatusById(status,id) > 0 ? 1 : -1;
    }
}
