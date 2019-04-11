package tqs.log.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import tqs.log.base.ApiResponse;
import tqs.log.dao.UserMapper;
import tqs.log.entity.User;
import tqs.log.utils.HttpResult;

@Controller
@ResponseBody
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "notLogin")
    public HttpResult<String> notLogin(){
        return new HttpResult().success("您尚未登录",null);
    }

    @GetMapping(value = "notRole")
    public HttpResult<String> notRole(){
        return new HttpResult().success("您没有权限",null);
    }

    @GetMapping(value = "logout")
    public ApiResponse logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ApiResponse().ofMessage(200, "注销成功");
    }

    @PostMapping(value = "login")
    public ApiResponse login(@RequestBody User user){

        String username = user.getUsername();
        String password = user.getPassword();

        Subject  subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

        String role = userMapper.getRoleByName(username);
        //根据权限返回特定的数据
        if ("user".equals(role)){
            return new ApiResponse().ofStatus(ApiResponse.Status.SUCCESS);
        }
        if ("admin".equals(role)){
            return new ApiResponse().ofMessage(50001, "欢迎来到管理员界面");
        }
        return new ApiResponse().ofMessage(50002, "权限错误");
    }
}
