package tqs.log.model;

import lombok.Data;

import java.util.Date;

@Data
public class PrivilegeModel {

    //    权限id
    private int id;
    //    用户id
    private int userId;
    private String username;
    private String servername;
    //    服务器id
    private int nginxId;
    //    创建时间
    private Date createdAt;
//    //    更新时间
//    private Date updated_at;
    //    是否发布
    private Boolean published;

    public PrivilegeModel() {
    }
}
