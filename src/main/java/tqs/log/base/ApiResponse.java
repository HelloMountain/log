package tqs.log.base;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/*
* Api结构设计
* */
//@Component
public class ApiResponse implements Serializable {

    private int code;
    private Object data;
    private String message;
    private boolean more;

    public ApiResponse(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ApiResponse(int code, Object data, String message, boolean more) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.more = more;
    }

    public ApiResponse() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getStandardMessage();
    }

    public enum Status{
        SUCCESS(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "Not Found"),
        INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
        NOT_VALID_PARAM(40005, "Not valid Params"),
        NOT_SUPPORTED_OPERATION(40006, "Operation not supported"),
        NOT_LOGIN(50000, "Not Login");

        private int code;
        private String standardMessage;

        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStandardMessage() {
            return standardMessage;
        }

        public void setStandardMessage(String standardMessage) {
            this.standardMessage = standardMessage;
        }

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public ApiResponse ofSuccess(Object data){
        return new ApiResponse(Status.SUCCESS.getCode(), data,null,false);
    }

    public ApiResponse ofMessage(int code, String message){
        return new ApiResponse(code, null, message);
    }

    public ApiResponse ofStatus(Status status){
        return new ApiResponse(status.getCode(), null, status.getStandardMessage());
    }

//    @Override
//    public String toString() {
//        return "ApiResponse{" +
//                "code=" + code +
//                ", data=" + data +
//                ", message='" + message + '\'' +
//                ", more=" + more +
//                '}';
//    }


//    private int code;
//    private String message;
//    private Object data;
//    private boolean more;
//
//    public ApiResponse(int code, String message, Object data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }
//
//    public ApiResponse() {
//        this.code = Status.SUCCESS.getCode();
//        this.message = Status.SUCCESS.getStandardMessage();
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    public boolean isMore() {
//        return more;
//    }
//
//    public void setMore(boolean more) {
//        this.more = more;
//    }
//
//    public static ApiResponse ofMessage(int code, String message) {
//        return new ApiResponse(code, message, null);
//    }
//
//    public static ApiResponse ofSuccess(Object data) {
//        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
//    }
//
//    public static ApiResponse ofStatus(Status status) {
//        return new ApiResponse(status.getCode(), status.getStandardMessage(), null);
//    }
//
//    public enum Status {
//        SUCCESS(200, "OK"),
//        BAD_REQUEST(400, "Bad Request"),
//        NOT_FOUND(404, "Not Found"),
//        INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
//        NOT_VALID_PARAM(40005, "Not valid Params"),
//        NOT_SUPPORTED_OPERATION(40006, "Operation not supported"),
//        NOT_LOGIN(50000, "Not Login");
//
//        private int code;
//        private String standardMessage;
//
//        Status(int code, String standardMessage) {
//            this.code = code;
//            this.standardMessage = standardMessage;
//        }
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public String getStandardMessage() {
//            return standardMessage;
//        }
//
//        public void setStandardMessage(String standardMessage) {
//            this.standardMessage = standardMessage;
//        }
//    }

}
