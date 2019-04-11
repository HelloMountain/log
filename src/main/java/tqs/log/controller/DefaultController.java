package tqs.log.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tqs.log.base.ApiResponse;
import tqs.log.utils.HttpResult;

@Controller
@ResponseBody
public class DefaultController {

    @GetMapping(value = "/default")
    public ApiResponse hello(){
        System.out.println("default");
        return new ApiResponse().ofStatus(ApiResponse.Status.NOT_LOGIN);
    }


    @GetMapping(value = "/default2")
    public String hello2(){
        System.out.println("default2");
        return "default2";
    }
}
