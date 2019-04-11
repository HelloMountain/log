package tqs.log.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "通用返回对象")
public class HttpResult<T> {

    @ApiModelProperty(value = "返回码", dataType = "int")
    int status = 0;

    @ApiModelProperty(value = "错误信息", dataType = "String")
    String msg = "";

    @ApiModelProperty(value = "返回数据", dataType = "T")
    T data = null;

    public HttpResult() {
    }

    public HttpResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public HttpResult(int status) {
        this.status = status;
    }

    public HttpResult(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public HttpResult(T data) {
        this.data = data;
    }

    public static <T> HttpResult<T> success(String msg, T data) {
        return new HttpResult<>(1, msg, data);
    }

    public static <T> HttpResult<T> fail(String msg, T data) {
        return new HttpResult<>(0, msg, data);
    }
}
