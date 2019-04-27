package tqs.log.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "testgoods",type = "goods")
public class GoodsInfo implements Serializable {
    private Long id;
    private String name;
    private String description;

    public GoodsInfo() {
    }

    public GoodsInfo(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
