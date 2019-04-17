package tqs.log.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tqs.log.base.ApiResponse;
import tqs.log.dao.UserMapper;
import tqs.log.entity.User;
import tqs.log.model.LoginModel;
import tqs.log.model.UserModel;
import tqs.log.model.request.LoginRequest;
import tqs.log.service.UserService;
import tqs.log.utils.HttpResult;

@Controller
@ResponseBody
@RequestMapping(value = "/api")
@Api(tags = "用户登录接口", description = "xxx")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "notLogin")
    @ApiOperation(value = "尚未登录", notes = "尚未登录接口")
    public ApiResponse notLogin() {
        return new ApiResponse().ofMessage(200, "您尚未登录");
    }

    @GetMapping(value = "notRole")
    public HttpResult<String> notRole() {
        return new HttpResult().success("您没有权限", null);
    }

    @GetMapping(value = "logout")
    public ApiResponse logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ApiResponse().ofMessage(200, "注销成功");
    }

    @PostMapping(value = "/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

//        String role = userMapper.getRoleByName(username);
        String role = "user";
        UserModel usermodel = userService.findUserByUserName(username);
        LoginModel loginModel = modelMapper.map(usermodel, LoginModel.class);
        //根据权限返回特定的数据
        if ("user".equals(role)) {
            return new ApiResponse(200, loginModel, "欢迎进入");
        }
        if ("admin".equals(role)) {
            return new ApiResponse(200, loginModel, "欢迎来到管理员界面");
//            return new ApiResponse().ofMessage(50001, "欢迎来到管理员界面");
        }
        return new ApiResponse().ofMessage(50002, "权限错误");
    }
}
