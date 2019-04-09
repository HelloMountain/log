package tqs.log.model.request;

import lombok.Data;

@Data
public class NginxRequestCreate {

    private String name;
    private String logPath;

    public NginxRequestCreate() {
    }

    public NginxRequestCreate(String name, String logPath) {
        this.name = name;
        this.logPath = logPath;
    }
}
