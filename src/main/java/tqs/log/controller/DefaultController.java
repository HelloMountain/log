package tqs.log.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tqs.log.base.ApiResponse;
import tqs.log.entity.User;
import tqs.log.model.TestModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        String leader = "curl -o /tmp/logCoat.zip -L"+ downloadUrl;
        leader = "if [ -z ${'$'}JAVA_HOME ];then" +
                "echo 'I require ${'$'}JAVA_HOME variable setting but it's not exists.  Aborting.';" +
                "exit 1;" +
                "fi";
        return new ApiResponse(200, leader, "安装引导程序");
    }

    @GetMapping(value = "/sh")
//    public String downloadFile(@RequestParam(value = "ID")String id, HttpServletRequest request, HttpServletResponse response) {
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        //获取server相关信息


        //生成对应的installl.sh

        //提供下载
//            String fileName = "install.sh";// 文件名
            String fileName = "log2.txt";// 文件名
            if (fileName != null) {
            //设置文件路径//C:\Users\qingshan\Desktop\test_4.zip
//            File file = new File("C:/Users/qingshan/log/zipTest2/conf/install.sh");
//            File file = new File("C:/Users/qingshan/Desktop/test_4.zip");
            File file = new File("C:/Users/qingshan/Desktop/log2.txt");
        //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    if (os != null){
                        os.close();
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @GetMapping(value = "/sh2")
    public ApiResponse downloadFile2(@RequestParam(value = "ID")int id, HttpServletResponse response){
        System.out.println(id);
        System.out.println(response.getStatus());
        return new ApiResponse(200, "id", "");
    }
}
