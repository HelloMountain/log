package tqs.log.model;

import lombok.Data;

import java.util.Date;

@Data
public class PUVModel {
    private Date day;
    private int pvNum;
    private int uvNum;
}
