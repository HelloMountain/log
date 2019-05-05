package tqs.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tqs.log.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "/api")
public class FileController {

    @Autowired
    private FileService fileService;

    /*
     * 网页跳转
     * */
    @GetMapping(value = "/index")
    @ResponseBody
    public String index() {
        return "index";
    }

    /*
     * 生成客户端安装脚本
     * */
    @GetMapping(value = "install/{uuid}/sh")
    @ResponseBody
    public String installSh(@RequestParam(value = "uuid") String uuid) {
        return fileService.installSh("path", uuid);
    }

    /*
     * 生成logcoat核心文件
     * */
    @GetMapping(value = "install/{uuid}/file")
    @ResponseBody
    public void file(@RequestParam(value = "uuid") String uuid) {

    }


    /*
     * 文件上传
     * */


    /*
     * 文件下载
     * */
    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
//        String fileName = "apache-flume-1.8.0-bin.tar.gz";// 文件名
        String fileName = "zipTest3.zip";// 文件名
        if (fileName != null) {
            //设置文件路径
//            File file = new File("C:/Users/qingshan/Downloads/apache-flume-1.8.0-bin.tar.gz");
            File file = new File("C:\\Users\\qingshan\\log\\zipTest2.zip");
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


}
