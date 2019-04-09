package tqs.log.service.impl;

import org.springframework.stereotype.Service;
import tqs.log.service.FlumeService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FlumeServiceImpl implements FlumeService {
    @Override
    public String install() {
        return null;
    }
    //    @Override
//    public String install() {

//        String bash = "            #!/bin/bash\n" +
//                "            if [ -z ${'$'}JAVA_HOME ];then\n" +
//                "                echo \"I require ${'$'}JAVA_HOME variable setting but it's not exists.  Aborting.\"\n" +
//                "                exit 1\n" +
//                "            fi\n" +
//                "            curl -o /tmp/logCoat.zip -L '$downloadUrl$uuid/file'\n" +
//                "            mkdir -p /usr/local/opt/logCoat\n" +
//                "            cd /usr/local/opt/logCoat\n" +
//                "            ${'$'}JAVA_HOME/bin/jar xvf /tmp/logCoat.zip\n" +
//                "            rm -rf /tmp/logCoat.zip\n" +
//                "            chmod 755 /usr/local/opt/logCoat/bin/startup.sh\n" +
//                "            chmod 755 /usr/local/opt/logCoat/bin/flume-ng\n" +
//                "            /usr/local/opt/logCoat/bin/startup.sh";
//        return bash;
//    }
//
//    /*
//    * 生成flume配置文件
//    * */
//    public String flumeProperties(){
//
//        String fileName =  "example.conf";
//        String content = "" +
//                "            agent.sources = s1\n" +
//                "            agent.channels = c1\n" +
//                "            agent.sinks = r1\n" +
//                "\n" +
//                "            agent.sources.s1.channels = c1\n" +
//                "            agent.sinks.r1.channel = c1\n" +
//                "\n" +
//                "            agent.sources.s1.type = TAILDIR\n" +
//                "            agent.sources.s1.positionFile = /usr/local/opt/logCoat/taildir_position.json\n" +
//                "\n" +
//                "            agent.sources.s1.filegroups = f1\n" +
//                "            agent.sources.s1.filegroups.f1=$logPath\n" +
//                "            agent.sources.s1.fileHeader = true\n" +
//                "\n" +
//                "            agent.channels.c1.type = memory\n" +
//                "            agent.channels.c1.capacity = 1000000\n" +
//                "            agent.channels.c1.transactionCapacity = 100\n" +
//                "            agent.channels.c1.keep-alive = 3\n" +
//                "\n" +
//                "            agent.sinks.r1.type = org.apache.flume.sink.kafka.KafkaSink\n" +
//                "            agent.sinks.r1.kafka.topic = $topicName\n" +
//                "            agent.sinks.r1.kafka.bootstrap.servers = $kafkaServer\n" +
//                "            agent.sinks.r1.kafka.flumeBatchSize = 20\n" +
//                "            agent.sinks.r1.kafka.producer.acks = 1\n" +
//                "            agent.sinks.r1.kafka.producer.linger.ms = 1\n" +
//                "            agent.sinks.r1.kafka.producer.compression.type = snappy\n" +
//                "            agent.sinks.r1.channel = c1"
//                ;
//
//        generateFile("", fileName, content);
//        return fileName;
//    }
//
//
//    /*
//    * 生成TAILDIRsource日志记录文件
//    * */
//    public String generateTail(){
//        String content = "[]";
//        String fileName = "taildir_position.json";
//        generateFile("", fileName, content);
//        return fileName;
//    }
//
//    /*
//    * 生成启动文件
//    * */
//    public String generateSetupSh(){
//        String content = "" +
//                "            #!/bin/bash\n" +
//                //todo 需要修改
//                "            eval nohup /usr/local/opt/logCoat/bin/flume-ng agent -n agent -c /usr/local/opt/logCoat/conf -f /usr/local/opt/logCoat/conf/flume-conf.properties -Djava.security.auth.login.config=/usr/local/opt/logCoat/conf/kafka_plain_jaas.conf > /usr/local/opt/logCoat/nohup.out 2>&1 &\n" +
//                "            echo \"install success !!\"";
//        String fileName = "setup.sh";
//        generateFile("", fileName, content);
//        return fileName;
//    }
//
//    /*
//    * 生成文件
//    * */
//    private void generateFile (String rootFile, String fileName, String content){
//
//        Path rootLocation = Paths.get(rootFile);
//        if (Files.notExists(rootLocation)){
//            try {
//                Files.createDirectory(rootLocation);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Path path = rootLocation.resolve(fileName);
//        byte[] bytes = content.getBytes();
//        try {
//            Files.write(path, bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
