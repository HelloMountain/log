package tqs.log.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tqs.log.service.FlumeService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FlumeController {

    @Autowired
    private FlumeService flumeService;


    /*
    * 获得安装flume说明
    * */
    @GetMapping(value = "/flume/document")
    public String document(){
        return "";
    }

    /*
    * 获得flume监控数据
    * */


}
