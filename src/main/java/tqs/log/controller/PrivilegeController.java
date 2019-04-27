package tqs.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tqs.log.base.ApiResponse;
import tqs.log.model.PrivilegeModel;
import tqs.log.model.request.PrivilegeRequest;
import tqs.log.service.PrivilegeService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;
    /*
    * 查看所有privilege
    * */
    @GetMapping(value = "findAll")
    public ApiResponse findAll(){
        List<PrivilegeModel> privilegeModels = privilegeService.findAll();
        return new ApiResponse(200, privilegeModels, "查找成功");
    }

    /*
    * 根据userId查看privilege
    * */
    @GetMapping(value = "findByUserId")
    public ApiResponse findByUserId(@RequestParam(value = "userId") int userId){
        List<PrivilegeModel> privilegeModels = privilegeService.findByUserId(userId);
        return new ApiResponse(200, privilegeModels, "查找成功");
    }

    /*
     * 根据userId查看privilege
     * */
    @GetMapping(value = "findByUserName")
    public ApiResponse findByUserName(@RequestParam(value = "username") String username){
        List<PrivilegeModel> privilegeModels = privilegeService.findByUsername(username);
        return new ApiResponse(200, privilegeModels, "查找成功");
    }

    /*
    * 根据serverId查看privilege
    * */
    @GetMapping(value = "findByNginxId")
    public ApiResponse findByServerId(@RequestParam(value = "nginxId") int nginxId){
        List<PrivilegeModel> privilegeModels = privilegeService.findByUserId(nginxId);
        return new ApiResponse(200, privilegeModels, "查找成功");
    }

    /*
     * 根据servername查看privilege
     * */
    @GetMapping(value = "findByNginxName")
    public ApiResponse findByNginxName(@RequestParam(value = "nginxname") String nginxname){
        List<PrivilegeModel> privilegeModels = privilegeService.findByServername(nginxname);
        return new ApiResponse(200, privilegeModels, "查找成功");
    }

    /*
    * 删除,根据privilegeIds
    * */
        @GetMapping("/deleteByIds")
    public ApiResponse deleteByIds(@RequestParam(value = "ids")Integer[] ids){
        int n = privilegeService.deleteIds(ids);
        return new ApiResponse(200, n, "删除成功");
    }

    /*
    * 增加privilege
    * */
    @PostMapping(value = "/add")
    public ApiResponse add(@RequestBody PrivilegeRequest.Create create){
        int n = privilegeService.add(create);
        return new ApiResponse(200, n, "添加成功");
    }

    /*
    * 修改privilege
    * */
    @PostMapping(value = "/update")
    public ApiResponse update(@RequestBody PrivilegeRequest.Update update){
        int n = privilegeService.update(update);
        return new ApiResponse(200, n, "更新成功");
    }

}


