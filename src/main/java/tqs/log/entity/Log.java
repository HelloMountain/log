package tqs.log.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "nginx", type = "doc")
public class Log implements Serializable {

    private int id;
//    时间戳
    private Date timestamp;
//    版本号
    private String version;
//    客户端请求地址
    private String client;
//    请求中的当前URI
    private String url;
//    请求状态
    private String status;
//    请求地址
    private String domian;
//    HTTP请求行的主机名>HOST 请求头字段
    private String host;
//    文件内容大小
    private String size;
//    处理客户端请求使用的时间,单位为秒
    private String responsetime;
//    哪个页面链接访问过来的
    private String referer;
//    客户端浏览器相关信息
    private String ua;

    public Log() {
    }

    public Log(int id, Date timestamp, String version, String client, String url, String status, String domian, String host, String size, String responsetime, String referer, String ua) {
        this.id = id;
        this.timestamp = timestamp;
        this.version = version;
        this.client = client;
        this.url = url;
        this.status = status;
        this.domian = domian;
        this.host = host;
        this.size = size;
        this.responsetime = responsetime;
        this.referer = referer;
        this.ua = ua;
    }

    public Log(Date timestamp, String version, String client, String url, String status, String domian, String host, String size, String responsetime, String referer, String ua) {
        this.timestamp = timestamp;
        this.version = version;
        this.client = client;
        this.url = url;
        this.status = status;
        this.domian = domian;
        this.host = host;
        this.size = size;
        this.responsetime = responsetime;
        this.referer = referer;
        this.ua = ua;
    }
}
