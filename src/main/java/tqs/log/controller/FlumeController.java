package tqs.log.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tqs.log.base.ApiResponse;
import tqs.log.service.FlumeService;


@Controller
public class FlumeController {

    @Autowired
    private FlumeService flumeService;

    /*
     * 获得安装flume说明
     * */
    @GetMapping(value = "/flume/document")
    public String document() {
        return "";
    }

    /*
     * 获得flume监控数据
     * */

    /*
    * 客户端安装
    * */
    @GetMapping(value = "/install")
    public ApiResponse installFlume(){
        return new ApiResponse(200, null, "");
    }


}
