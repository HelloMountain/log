package tqs.log.service.impl;

import org.springframework.stereotype.Service;
import tqs.log.service.FileService;
import tqs.log.utils.ZipUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    //最原始的文件路径
    private static String souceFlumePath = "C:\\Users\\qingshan\\Downloads\\apache-flume-1.8.0-bin.tar\\apache-flume-1.8.0-bin";

    //供压缩的文件路径
    private static String logCoatRoot = "C:\\Users\\qingshan\\Desktop\\zipTest2"; // logCoat 的保存地址

   // 压缩后供下载的文件路径
    private static String logCoatRootZip = "C:\\Users\\qingshan\\Desktop\\flume.zip";


    @Override
    public String generateCore(String uuid) {
        String path = logCoatRoot + uuid;
        if (new File(path).exists()){
            return path;
        }

        String flumePropertiesPath = this.flumeConfig(path);
        String generateTailPath = this.generateTail(path);
        String installShPath = this.installSh(uuid, path);
        return path;
    }

    @Override
    public String installSh(String uuid, String path) {
        String fileName = "install.sh";
        String bash = "            #!/bin/bash\n" +
                "            if [ -z ${'$'}JAVA_HOME ];then\n" +
                "                echo \"I require ${'$'}JAVA_HOME variable setting but it's not exists.  Aborting.\"\n" +
                "                exit 1\n" +
                "            fi\n" +
                "            curl -o /tmp/logCoat.zip -L '$downloadUrl$uuid/file'\n" +
                "            mkdir -p /usr/local/opt/logCoat\n" +
                "            cd /usr/local/opt/logCoat\n" +
                "            ${'$'}JAVA_HOME/bin/jar xvf /tmp/logCoat.zip\n" +
                "            rm -rf /tmp/logCoat.zip\n" +
                "            chmod 755 /usr/local/opt/logCoat/bin/startup.sh\n" +
                "            chmod 755 /usr/local/opt/logCoat/bin/flume-ng\n" +
                "            /usr/local/opt/logCoat/bin/startup.sh";
        generateFile(logCoatRoot + "\\" + "conf", fileName, bash);
        return fileName;
    }

    /*
     * 生成flume配置文件
     * */
    public String flumeConfig(String path){

        String fileName =  "nginx.conf";
        String content = "" +
                "agent.sources = s1\n" +
                "agent.channels = c1\n" +
                "agent.sinks = r1\n" +
                "\n" +
                "agent.sources.s1.channels = c1\n" +
                "agent.sinks.r1.channel = c1\n" +
                "\n" +
                "agent.sources.s1.type = TAILDIR\n" +
                "agent.sources.s1.positionFile = /usr/local/opt/logCoat/taildir_position.json\n" +
                "\n" +
                "agent.sources.s1.filegroups = f1\n" +
                "agent.sources.s1.filegroups.f1=$logPath\n" +
                "agent.sources.s1.fileHeader = true\n" +
                "\n" +
                "agent.channels.c1.type = memory\n" +
                "agent.channels.c1.capacity = 1000000\n" +
                "agent.channels.c1.transactionCapacity = 100\n" +
                "agent.channels.c1.keep-alive = 3\n" +
                "\n" +
                "agent.sinks.r1.type = org.apache.flume.sink.kafka.KafkaSink\n" +
                "agent.sinks.r1.kafka.topic = $topicName\n" +
                "agent.sinks.r1.kafka.bootstrap.servers = $kafkaServer\n" +
                "agent.sinks.r1.kafka.flumeBatchSize = 20\n" +
                "agent.sinks.r1.kafka.producer.acks = 1\n" +
                "agent.sinks.r1.kafka.producer.linger.ms = 1\n" +
                "agent.sinks.r1.kafka.producer.compression.type = snappy\n" +
                "agent.sinks.r1.channel = c1"
                ;

        generateFile(logCoatRoot + "\\" + "conf", fileName, content);
        return fileName;
    }


    /*
     * 生成TAILDIRsource日志记录文件
     * */
    public String generateTail(String path){
        String content = "[]";
        String fileName = "taildir_position.json";
        generateFile(logCoatRoot, fileName, content);
        return fileName;
    }

    /*
     * 生成启动文件
     * */
    public String generateSetupSh(String path){
        String content = "" +
                "            #!/bin/bash\n" +
                //todo 需要修改
                "            eval nohup /usr/local/opt/logCoat/bin/flume-ng agent -n agent -c /usr/local/opt/logCoat/conf -f /usr/local/opt/logCoat/conf/flume-conf.properties -Djava.security.auth.login.config=/usr/local/opt/logCoat/conf/kafka_plain_jaas.conf > /usr/local/opt/logCoat/nohup.out 2>&1 &\n" +
                "            echo \"install success !!\"";
        String fileName = "setup.sh";
        generateFile(logCoatRoot, fileName, content);
        return fileName;
    }

    /*
     * 生成文件
     * */
    private void generateFile (String rootFile, String fileName, String content){

        Path rootLocation = Paths.get(rootFile);
        if (Files.notExists(rootLocation)){
            try {
                Files.createDirectory(rootLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path path = rootLocation.resolve(fileName);
        byte[] bytes = content.getBytes();
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //复制文件
    private String copyFile(String sourceFile, String destFile)throws Exception{

        FileInputStream fileInputStream = new FileInputStream(new File(sourceFile));
        FileOutputStream fileOutputStream = new FileOutputStream(new File(destFile));

        int tag;
        while ((tag = fileInputStream.read()) != -1){
            fileOutputStream.write(tag);
        }
        fileOutputStream.close();
        fileInputStream.close();
        return destFile;
    }

    //复制目录下的所有文件
    private String copyDir (String sourcePath, String destPath)throws Exception{
        File file = new File(sourcePath);
        File[] files = file.listFiles();

        if (!new File(destPath).exists()){
            boolean mkdir = new File(destPath).mkdir();
        }
        for (File f : files) {
            if (f.isDirectory()){
                copyDir(f.getPath(), destPath + file.separator  + f.getName());
            }else {
                copyFile(f.getPath(), destPath + file.separator + f.getName());
            }
        }
        return destPath;
    }

    private String generateCoreZip()throws Exception{
        String corePath = generateCore("uuid");
        ZipUtil zipUtil = new ZipUtil(logCoatRootZip, corePath);
        zipUtil.zip();
        return corePath;
    }

    public static void main(String[] args) {
        try {
//            new FileServiceImpl().copyFile("C:\\Users\\qingshan\\Desktop\\zipTest\\1.txt", "C:\\Users\\qingshan\\Desktop\\zipTest\\copy.txt");
//            new FileServiceImpl().copyDir(souceFlumePath, "C:\\Users\\qingshan\\Desktop\\zipTest2");
//            FileServiceImpl service = new FileServiceImpl();
//            service.generateCore("uuid");
            ZipUtil zipUtil = new ZipUtil(logCoatRootZip, logCoatRoot);
            zipUtil.zip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
