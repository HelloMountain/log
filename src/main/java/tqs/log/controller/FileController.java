package tqs.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tqs.log.entity.Server;
import tqs.log.service.FileService;
import tqs.log.service.ServerService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "/api")
public class FileController {

    // 压缩后供下载的文件路径
    private static String logCoatRootZip = "C:\\Users\\qingshan\\Desktop\\test";

    @Autowired
    private FileService fileService;

    @Autowired
    private ServerService serverService;

    /*
     * 获取安装脚本
     * */
    @ResponseBody
    @GetMapping(value = "/sh")
    public String installSh(@RequestParam(value = "ID") int id, HttpServletResponse response){
        //获取server相关信息
        Server server = serverService.findById(id);
        //生成安装脚本
        String filePath = fileService.installSh(id+"");
        String fileName = "install.sh";
        //提供下载
        return download(id, response, fileName, filePath);
    }

    /*
     * 生成客户端安装脚本
     * */
    @GetMapping(value = "install/{uuid}/sh")
    @ResponseBody
    public String installSh(@RequestParam(value = "uuid") String uuid) {
        return fileService.installSh(uuid);
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
    @ResponseBody
    public String downloadFile(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse response) {
        //生成id对应的安装包
        Server server = serverService.findById(id);
        if (server == null){
            return "";
        }
        String filePath = logCoatRootZip + "_" + id + ".zip";
        File file = new File(filePath);
        if (!file.exists()){
            return "";
        }
        //提供下载
//        String fileName = "apache-flume-1.8.0-bin.tar.gz";// 文件名
        String fileName = "logCoat.zip";// 文件名,压缩包里面的文件的名字，不是压缩包名,压缩包名是curl -o确定的
        return download(id, response, fileName, filePath);
    }

    public String download(int id, HttpServletResponse response, String fileName, String filePath){
        System.out.println("download");
        if (fileName != null) {
            //设置文件路径
//            File file = new File("C:/Users/qingshan/Downloads/apache-flume-1.8.0-bin.tar.gz");
            File file = new File(filePath);
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
        return "下载失败";}

}
