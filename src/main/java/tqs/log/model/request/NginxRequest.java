package tqs.log.model.request;

public class NginxRequest {

    public static class Create{

        private String name;
        private String logPath;

        public Create() {
        }

        public Create(String name, String logPath) {
            this.name = name;
            this.logPath = logPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogPath() {
            return logPath;
        }

        public void setLogPath(String logPath) {
            this.logPath = logPath;
        }
    }

    public static class Update{

        private int id;
        private String uuid;
        private String name;
        private String logPath;
        private String host;

        public Update() {
        }

        public Update(int id, String uuid, String name, String logPath, String host) {
            this.id = id;
            this.uuid = uuid;
            this.name = name;
            this.logPath = logPath;
            this.host = host;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogPath() {
            return logPath;
        }

        public void setLogPath(String logPath) {
            this.logPath = logPath;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }
    }

}
