package tqs.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tqs.log.entity.Server;
import tqs.log.model.request.NginxRequest;
import tqs.log.service.ServerService;
import tqs.log.utils.HttpResult;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/nginx")
public class NginxController {

    @Autowired
    private ServerService serverService;

    /*
    * 添加服务器
    * */
    @PostMapping(value = "/create")
    @ResponseBody
    public int createNginx(@RequestBody NginxRequest.Create nginx, HttpServletRequest request){
        String host = request.getRemoteHost();
        return serverService.createServer(nginx, host);
    }

    /*
    * 修改服务器
    * */
    @PostMapping(value = "/update")
    @ResponseBody
    public HttpResult<Boolean> updateNginx(@RequestBody NginxRequest.Update nginx){
        return serverService.updateServer(nginx);
    }

    /*
    * 查找服务器
    * */
    @GetMapping(value = "/findById")
    @ResponseBody
    public HttpResult<Server> findById(@RequestParam(value = "id") int id){
        HttpResult<Server> result = serverService.findById(id);
        return result;
    }

    /*
    * 删除服务器
    * */
    @PostMapping(value = "/delete")
    @ResponseBody
    public HttpResult<Boolean> deleteById(@RequestParam(value = "id") int id){
        return serverService.deleteById(id);
    }
}
