package tqs.log.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tqs.log.base.ApiResponse;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/data")
public class DataController {

    @GetMapping(value = "/code")
    public ApiResponse code(){
        HashMap<String, Integer> code = new HashMap<>();
        code.put("400", 335);
        code.put("404", 310);
        code.put("503", 234);
        code.put("304", 135);
        code.put("200", 1548);
        code.put("其他", 50);

        String[] array = {"400", "335", "404", "310", "503", "234", "304", "135", "200", "1548"};

        return new ApiResponse(200, array, "");
    }

    @GetMapping(value = "/topIp")
    public ApiResponse topIp(){
        String[] array = {"112.6.224.44", "18203", "112.6.224.44", "23489","112.6.224.44", "29034", "112.6.224.44", "104970", "112.6.224.44", "131744","112.6.224.44", "131744"};
        return new ApiResponse(200, array, "");
    }
}
