package tqs.log.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tqs.log.base.ApiResponse;
import tqs.log.entity.User;
import tqs.log.model.TestModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "/api/test")
@Api("swagger ui 注释 api 级别")
public class DefaultController {

    private String downloadUrl = "localhost:8081/download";
    @ApiOperation("swagger ui 注释 方法级别")
    @RequestMapping(value="/hello",method=RequestMethod.GET)
    public  String hello1() {
        String data = "Never trouble untill trouble troubles you. Some one told me some years ago.";
        return data;
    }

    @GetMapping(value = "/default")
    public ApiResponse hello() {
        System.out.println("default");
        return new ApiResponse().ofStatus(ApiResponse.Status.NOT_LOGIN);
    }


    @GetMapping(value = "/default2")
    public String hello2() {
        System.out.println("default2");
        return "default2";
    }

    @PostMapping(value = "/login2")
    public Boolean login2(@RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        if (user.getUsername().equals("tqs") && user.getPassword().equals("123456")){
            return true;
        }else {
            return false;
        }
    }

    @GetMapping(value = "/user/list")
    public ApiResponse getUserList(){
        List<TestModel> users = new ArrayList<>();

        TestModel testModel1 = new TestModel("1", "1",1, "1", new Date(),1);
        TestModel testModel2 = new TestModel("2", "2",2, "2", new Date(),1);

        users.add(testModel1);
        users.add(testModel2);

        return new ApiResponse(200, users, "success");
    }

    @GetMapping(value = "/code")
    public ApiResponse getCode(){
        int[] data = {100, 200, 300, 400, 500, 600, 700, 700, 700, 700};
        return new ApiResponse(200, data, "状态码");
    }

    @GetMapping(value = "/leader")
    public ApiResponse leader(){
//        String  leader =  "curl -o /tmp/logCoat.zip -L ' "+ $downloadUrl$uuid/file'";
        String leader = "curl -o /tmp/logCoat.zip -L  "+ downloadUrl;
        return new ApiResponse(200, leader, "安装引导程序");
    }
}
