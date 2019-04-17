package tqs.log.model.request;

import lombok.Data;


public class NginxRequest {

    @Data
    public static class Create {

        private String name;
        private String logPath;
        private Boolean published;

        public Create() {
        }

        public Create(String name, String logPath, Boolean published) {
            this.name = name;
            this.logPath = logPath;
            this.published = published;
        }
    }

    @Data
    public static class Update {

        private int id;
        private String name;
        private String logPath;
        private Boolean published;

        public Update() {
        }

        public Update(int id, String name, String logPath, Boolean published) {
            this.id = id;
            this.name = name;
            this.logPath = logPath;
            this.published = published;
        }
    }

}
