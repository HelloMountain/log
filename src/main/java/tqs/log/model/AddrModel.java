package tqs.log.model;

import lombok.Data;

@Data
public class AddrModel {

    // 国家
    private String country;
    // 省会或直辖市（国内）
    private String capitol;
    // 地区或城市 （国内）
    private String city;
    // 国家// 学校或单位 （国内）
    private String company;

    private String temp;
}
