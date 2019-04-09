package tqs.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@TableName(value = "server")
public class Server {
    private long id;
    private String uuid;
    private String name;
    private String host;
    private String logPath;
    private Date createdAt;
    private Date updatedAt;
    private Boolean published;
}

