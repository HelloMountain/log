package tqs.log.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Addr implements Serializable {

    private int id;
    private int logId;
    private String ip;
    private String addr;
}
