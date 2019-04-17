package tqs.log.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tqs.log.base.ApiResponse;
import tqs.log.model.UserModel;
import tqs.log.model.request.UserRequest;
import tqs.log.service.UserService;

import java.util.List;

/*
 *
 * */

@Controller
@ResponseBody
@RequestMapping(value = "/api/user")
public class AdminController {

    @Autowired
    private UserService userService;

    /*
     * 添加用户
     * */
    @PostMapping(value = "/add")
    public ApiResponse addUser(@RequestBody UserRequest.Create create) {
        int n = userService.createUser(create);
        return n > 0 ? new ApiResponse().ofStatus(ApiResponse.Status.SUCCESS) : new ApiResponse(200, create, "添加用户失败");
    }

    /*
     * 删除用户
     * */
    @GetMapping(value = "/delete")
    public ApiResponse deleteUser(@RequestParam(value = "id") int id) {
        int n = userService.deleteUser(id);
        return n > 0 ? new ApiResponse().ofStatus(ApiResponse.Status.SUCCESS) : new ApiResponse(200, id, "删除用户失败");
    }

    /*
    * 批量删除
    * */
    @GetMapping(value = "/batchdelete")
    public ApiResponse batchDelete(@RequestParam(value = "ids")Integer[] ids){
        int n = userService.batchDelete(ids);
        return n > 0 ? new ApiResponse().ofMessage(200, "删除成功"): new ApiResponse().ofMessage(200, "删除失败");
    }

    /*
     * 修改用户
     * */
    @PostMapping(value = "/update")
    public ApiResponse pudateUser(@RequestBody UserRequest.Update update) {
        int n = userService.updateUser(update);
        return n > 0 ? new ApiResponse().ofStatus(ApiResponse.Status.SUCCESS) : new ApiResponse(200, update, "修改用户失败");
    }

    /*
     * 通过用户名模糊查找用户
     * */
    @GetMapping(value = "/findByUserName")
    public ApiResponse findByUserName(@RequestParam(value = "username") String username) {
        List<UserModel> result = userService.findByUserName(username);
        return new ApiResponse().ofSuccess(result);
    }

    /*
     * 查找所有用户
     * */
    @GetMapping(value = "/list")
    public ApiResponse findAll() {
        List<UserModel> result = userService.findAll();
        return new ApiResponse().ofSuccess(result);
    }


}
