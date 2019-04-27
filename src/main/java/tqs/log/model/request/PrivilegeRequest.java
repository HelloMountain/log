package tqs.log.model.request;

import lombok.Data;

import java.util.Date;


public class PrivilegeRequest {

    @Data
    public static class Create{
    private int userId;
    private int nginxId;
    }

    @Data
    public static class Update{

        private int id;
        private int userId;
        private int nginxId;
        private Date created_at;
        private Date updated_at;
        private Boolean published;
    }
}
