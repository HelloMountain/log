package tqs.log.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tqs.log.base.ApiResponse;
import tqs.log.entity.Server;
import tqs.log.model.ServerModel;
import tqs.log.model.request.NginxRequest;
import tqs.log.service.FileService;
import tqs.log.service.ServerService;
import tqs.log.utils.HttpResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/nginx")
public class NginxController {

    @Autowired
    private ServerService serverService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelMapper modelMapper;


    /*
     * 添加服务器
     * */
    @PostMapping(value = "/create")
    @ResponseBody
    public ApiResponse createNginx(@RequestBody NginxRequest.Create nginx, HttpServletRequest request) {

        List<Server> servers = serverService.findByLikeName(nginx.getName());
        if (servers != null && servers.size() > 0){
            return new ApiResponse(400, null, "已存在该服务器");
        }
//        String host = request.getRemoteHost();

        //todo: 获取创建服务器信息，创建对应的客户端压缩包
        int n = serverService.createServer(nginx);
        long id = serverService.findByName(nginx.getName()).getId();
        fileService.generateCore(id+"", nginx.getLogPath());
        return  n > 0 ? new ApiResponse(200, id, "添加成功"):new ApiResponse().ofMessage(200, "添加失败");
    }

    /*
     * 修改服务器
     * */
    @PostMapping(value = "/update")
    @ResponseBody
    public ApiResponse updateNginx(@RequestBody NginxRequest.Update nginx) {
        int n = serverService.updateServer(nginx);
        return n > 0 ? new ApiResponse().ofMessage(200, "更新成功"):new ApiResponse().ofMessage(200, "更新失败");
    }

    /*
     * 通过id查找服务器
     * */
    @GetMapping(value = "/findById")
    @ResponseBody
    public ApiResponse findById(@RequestParam(value = "id") int id) {
        Server result = serverService.findById(id);
        return new ApiResponse(200, result, "查询成功");
    }

    /*
    * 通过服务器名模糊查找
    * */
    @GetMapping(value = "/findByName")
    @ResponseBody
        public ApiResponse findByName(@RequestParam(value = "name") String name){
        List<Server> data = serverService.findByLikeName(name);
        ApiResponse result = new ApiResponse().ofSuccess(data);
        return result;
    }

    /*
    * 查找所有服务器
    * */
    @GetMapping(value = "/findAll")
    @ResponseBody
    public ApiResponse findAll(){
        List<Server> servers = serverService.findAll();
        List<ServerModel> result = new ArrayList<>();
        servers.forEach(server -> {
            result.add(modelMapper.map(server, ServerModel.class));
        });
        return new ApiResponse().ofSuccess(result);
    }


    /*
     * 删除服务器
     * */
    @GetMapping(value = "/delete")
    @ResponseBody
    public ApiResponse deleteById(@RequestParam(value = "id") int id) {

        int n = serverService.deleteById(id);
        return n > 0? new ApiResponse(200, n, "删除成功"):new ApiResponse(200, n, "删除失败");
    }

    /*
     * 批量删除
     * */
    @GetMapping(value = "/batchdelete")
    @ResponseBody
    public ApiResponse batchDelete(@RequestParam(value = "ids")Integer[] ids){
        int n = serverService.batchDelete(ids);
        return n > 0 ? new ApiResponse().ofMessage(200, "删除成功"): new ApiResponse().ofMessage(200, "删除失败");
    }
}
