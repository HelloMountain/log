package tqs.log.model.request;

import lombok.Data;

@Data
public class NginxRequestUpdate {

    private int id;
    private String uuid;
    private String name;
    private String logPath;
    private String host;

    public NginxRequestUpdate() {
    }

    public NginxRequestUpdate(int id, String uuid, String name, String logPath, String host) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.logPath = logPath;
        this.host = host;
    }
}
