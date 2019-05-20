package tqs.log.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Browser implements Serializable {
    private int id;
    private String browser;
}
