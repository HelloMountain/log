package tqs.log.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
public class Privilege implements Serializable{

//      权限id
    private int id;
//    用户id
    private int userId;
//    服务器id
    private int nginxId;
//    创建时间
    private Date created_at;
//    更新时间
    private Date updated_at;
//    是否发布
    private Boolean published;

    public Privilege() {
    }
}
