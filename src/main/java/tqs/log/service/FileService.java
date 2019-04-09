package tqs.log.service;

import org.springframework.stereotype.Service;

@Service
public interface FileService {

    //生成客户端安装脚本
    String installSh(String path, String uuid);

    /*
    * 生成logcoat核心
    * */
    String generateCore(String uuid);
}
