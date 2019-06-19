package tqs.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tqs.log.base.ApiResponse;
import tqs.log.model.UserModel;
import tqs.log.model.request.UserRequest;
import tqs.log.service.UserService;

import java.util.ArrayList;
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
        if ("1".equals(create.getRole())){
            create.setRole("user");
        }else {
            create.setRole("admin");
        }
        create.setAvatar("https://raw.githubusercontent.com/wiki/HelloMountain/ImageBed/vue/avatar.jpg");
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
    @GetMapping(value = "/likeByUserName")
    public ApiResponse findByUserName(@RequestParam(value = "username") String username) {
        List<UserModel> result = userService.likeUserByUserName(username);
        return new ApiResponse().ofSuccess(result);
    }

    /*
     * 查找所有用户
     * */
    @GetMapping(value = "/list")
    public ApiResponse findAll(@RequestParam(value = "name") String name) {
//        System.out.println(name);
//        System.out.println(name.equals(""));
        List<UserModel> result = null;
        if (!name.equals("")){
            result = userService.likeUserByUserName(name);
        }else {
            result = userService.findAll();
        }
        return new ApiResponse().ofSuccess(result);
    }

    /*
    * 返回用户自己的信息
    * */
    @GetMapping(value = "/selfInfo")
    public ApiResponse getSelfInfo(@RequestParam(value = "id") int id){
        List<UserModel> userModels = new ArrayList<>();
        userModels.add(userService.findById(id));
        return new ApiResponse(200, userModels, "我的信息");
    }
}
