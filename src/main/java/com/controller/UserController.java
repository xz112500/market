package com.controller;

import com.Service.Impl.UserServiceImpl;
import com.Service.ProducerService;
import com.alibaba.fastjson.JSONObject;
import com.config.MailPost;
import com.pojo.User;
import com.pojo.Vo.PageQuery;
import com.pojo.Vo.RegisterVo;
import com.pojo.Vo.UserVo;
import com.util.IdWorkers;
import com.util.JWTUtil;
import com.util.RabbitmqUtils;
import com.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    ResultUtil<Object> resultUtil=new ResultUtil<>();
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private ProducerService producerService;
    @Autowired
    private MailPost mailPost;
    @ApiOperation(value = "通过用户名模糊查询")
    @GetMapping(value = "/query")
    public ResultUtil<Object> user(@RequestParam(value = "username") String username){
       if (StringUtils.isNotBlank(username)){
           List<User> list=userService.QueryByName(username);
           return list != null ? resultUtil.ok(list.get(0)) : resultUtil.message("查无此人!");
       }return resultUtil.error().message("请输入关键字!");
    }
    @ApiOperation(value = "带用户名分页查询")
    @GetMapping(value = "/page")
    public ResultUtil<Object> page(PageQuery pageQuery){
        Page<User> page=userService.PageQuery(pageQuery.getUsername(),pageQuery.getPageNum()-1, pageQuery.getPageSize());
       return new ResultUtil<>().ok(page);
    }
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete")
    public ResultUtil<Object> delete(@ApiParam(name = "id",value = "用户id") Integer id){
        userService.delete(id);
        return new ResultUtil<>().message("删除成功");
    }
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/save")
    public ResultUtil<Object> save(UserVo user){
        if (userService.QueryByName(user.getUsername()) == null){
            return resultUtil.ok(userService.save(user));
        }else{
            return resultUtil.error().message("用户名已存在");
        }
    }
    @PostMapping(value = "/update")
    public ResultUtil<Object> update(UserVo userVo){
        if (userVo.getId() == null){
            return resultUtil.error().message("请输入信息!");
        }else {
             userService.update(userVo);
            return resultUtil.message("修改成功!");
        }
    }
    @ApiOperation(value = "用户登录")
    @PostMapping("/Login")
    public ResultUtil<Object> Login(@ApiParam(name = "username",value = "用户名") String username,@ApiParam(name = "password",value = "密码") String password){
           if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
                   String enCodePass= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
                   UserVo user=userService.queryByUsernameAndPassword(username, enCodePass);
                   if (user != null){
                       try{
                           JWTUtil.parseToken(user.getToken());
                           return resultUtil.ok(user);
                       }catch (Exception e){
                           return resultUtil.error().message("token过期").status(406);
                       }
                   }else {
                       return resultUtil.error().message("账号或密码错误!");
                   }
               }
                   return resultUtil.error().message("账号或密码为空!").status(404);
    }
    @ApiOperation(value = "通过id修改用户状态")
    @PostMapping(value = "updateStatus")
    public ResultUtil<Object> updateStatus(@ApiParam(name = "status",value = "用户状态码") Integer status,Integer id){
        if (status == null || id == null){
            return resultUtil.error().message("请输入信息!").status(404);
        }else {
            return userService.updateStatusById(status,id) > 0 ? resultUtil.ok("修改成功!") : resultUtil.error().message("修改失败!");
        }
    }
    @GetMapping(value = "/test")
        public String test(String message){
            template.convertAndSend(RabbitmqUtils.FANOUT_EXCHANGES, "", message);
            return "OK,sendDirect:" + new IdWorkers(1,1).nextId();
        }
    @GetMapping(value = "/test2")
    public String test1(String message){
        template.convertAndSend(RabbitmqUtils.DIRECT_EXCHANGES,"email",message);
        return "发送成功";
    }
    @PostMapping(value = "/Register")
    public void Register(@RequestBody JSONObject jsonObject){
      producerService.sendMail(RabbitmqUtils.DIRECT_EXCHANGES,RabbitmqUtils.DIRECT_QUEUE1_KEY,jsonObject);
    }
    @PostMapping(value = "GetMail")
    public ResultUtil<Object> GetMail(String email){
        try {
            mailPost.MailPost(email);
            return resultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return resultUtil.error();
        }
    }
}
