package tqs.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ZipUtil zipUtil;

    //最原始的文件路径
    private static String souceFlumePath = "C:\\apache-flume-1.8.0-bin";

    //供压缩的文件路径
    private static String logCoatRoot = "C:\\apache-flume-1.8.0-bin"; // logCoat 的保存地址

    // 压缩后供下载的文件路径
    private static String logCoatRootZip = "C:\\Users\\qingshan\\Desktop\\test";

    //供外网访问的地址
    private static String downloadUrl = "10.241.150.159";

    //kafka
    private static String kafkaServer = "10.241.150.159:9092";

    //kafka_topic
    private static String kafkaTopicName = "qstopic";

    @Override
    public String generateCore(String id, String logPath){
//        String path = logCoatRoot;
//        String path = "C:\\Users\\qingshan\\Desktop\\test";
//        if (new File(path).exists()) {
//            return path;
//        }
//


        //复制文件
//        try {
//            copyDir(souceFlumePath, logCoatRoot);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //在原始的文件中创建自定义的文件

        //生成flume配置文件
        String flumePropertiesPath = this.flumeConfig(logPath);
//        //生成tail记录文件
        String generateTailPath = this.generateTail();
//        //生成用户执行脚本
        String installShPath = this.installSh(id);
//        //生成flume启动文件
        String generateSetupShPath = this.generateSetupSh();

        //压缩文件到指定目录
        try {
            zipUtil.setSourceFileName(logCoatRoot);
            zipUtil.setZipFileName(logCoatRootZip + "_" + id + ".zip");
            zipUtil.zip();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //删除自定义的文件
        System.out.println(flumePropertiesPath);
        System.out.println(generateTailPath);
        System.out.println(installShPath);
        System.out.println(generateSetupShPath);
        String s1 = "C:\\apache-flume-1.8.0-bin\\conf\\nginx.conf";
        String s2 = "C:\\apache-flume-1.8.0-bin\\taildir_position.json";
        String s3 = "C:\\apache-flume-1.8.0-bin\\conf\\install.sh";
        String s4 = "C:\\apache-flume-1.8.0-bin\\setup.sh";
        deleteOnlyFile(flumePropertiesPath);
        deleteOnlyFile(generateTailPath);
        deleteOnlyFile(installShPath);
        deleteOnlyFile(generateSetupShPath);
        return logCoatRootZip;
    }

    @Override
    public String installSh(String id) {
        String fileName = "install.sh";
        String bash = "            #!/bin/bash\n" +
                "            if [ -z ${'$'}JAVA_HOME ];then\n" +
                "                echo \"I require ${'$'}JAVA_HOME variable setting but it's not exists.  Aborting.\"\n" +
                "                exit 1\n" +
                "            fi\n" +
                "            curl -o /tmp/logCoat.zip -L " + downloadUrl+":8081/api/download?id="+id+"\n" +//10.241.150.159:8081/api/download
                "            mkdir -p /usr/local/opt/logCoat\n" +
                "            cd /usr/local/opt/logCoat\n" +
                "            ${'$'}JAVA_HOME/bin/jar xvf /tmp/logCoat.zip\n" +
                "            rm -rf /tmp/logCoat.zip\n" +
                "            chmod 755 /usr/local/opt/logCoat/bin/startup.sh\n" +
                "            chmod 755 /usr/local/opt/logCoat/bin/flume-ng\n" +
                "            /usr/local/opt/logCoat/bin/startup.sh";
        generateFile(logCoatRoot + "\\" + "conf", fileName, bash);
        String filePath = logCoatRoot + "\\" + "conf\\"+ fileName;
        return filePath;
    }

    /*
     * 生成flume配置文件
     * */
    public String flumeConfig(String logPath) {

        String fileName = "nginx.conf";
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
                "agent.sources.s1.filegroups.f1="+logPath+"\n" +
                "agent.sources.s1.fileHeader = true\n" +
                "\n" +
                "agent.channels.c1.type = memory\n" +
                "agent.channels.c1.capacity = 1000000\n" +
                "agent.channels.c1.transactionCapacity = 100\n" +
                "agent.channels.c1.keep-alive = 3\n" +
                "\n" +
                "agent.sinks.r1.type = org.apache.flume.sink.kafka.KafkaSink\n" +
                "agent.sinks.r1.kafka.topic = "+kafkaTopicName+"\n" +
                "agent.sinks.r1.kafka.bootstrap.servers = "+kafkaServer+"\n" +
                "agent.sinks.r1.kafka.flumeBatchSize = 20\n" +
                "agent.sinks.r1.kafka.producer.acks = 1\n" +
                "agent.sinks.r1.kafka.producer.linger.ms = 1\n" +
                "agent.sinks.r1.kafka.producer.compression.type = snappy\n" +
                "agent.sinks.r1.channel = c1";

        generateFile(logCoatRoot + "\\" + "conf", fileName, content);
        String filePath = logCoatRoot + "\\" + "conf"+ "\\" + fileName;
        return filePath;
    }


    /*
     * 生成TAILDIRsource日志记录文件
     * */
    public String generateTail() {
        String content = "[]";
        String fileName = "taildir_position.json";
        generateFile(logCoatRoot, fileName, content);
        String filePath = logCoatRoot + "\\" + fileName;
        return filePath;
    }

    /*
     * 生成启动文件
     * */
    public String generateSetupSh() {
        String content = "" +
                "            #!/bin/bash\n" +
                //todo 需要修改
                "#           eval nohup /usr/local/opt/logCoat/bin/flume-ng agent -n agent -c /usr/local/opt/logCoat/conf -f /usr/local/opt/logCoat/conf/flume-conf.properties  &\n" +
                "            /usr/local/opt/logCoat/apache-flume-1.8.0-bin/bin/flume-ng agent -c /usr/local/opt/logCoat/apache-flume-1.8.0-bin/conf/ -f /usr/local/opt/logCoat/apache-flume-1.8.0-bin/conf/kafka.conf -n agent -Dflume.root.logger=INFO,console\n" +
                "            echo \"install success !!\"";
        String fileName = "setup.sh";
        generateFile(logCoatRoot, fileName, content);
        String filePath = logCoatRoot + "\\" + fileName;
        return filePath;
    }

    /*
     * 生成文件
     * */
    private void generateFile(String rootFile, String fileName, String content) {

        Path rootLocation = Paths.get(rootFile);
        if (Files.notExists(rootLocation)) {
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
    private String copyFile(String sourceFile, String destFile) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(new File(sourceFile));
        FileOutputStream fileOutputStream = new FileOutputStream(new File(destFile));

        int tag;
        while ((tag = fileInputStream.read()) != -1) {
            fileOutputStream.write(tag);
        }
        fileOutputStream.close();
        fileInputStream.close();
        return destFile;
    }

    //复制目录下的所有文件
    public String copyDir(String sourcePath, String destPath) throws Exception {
        File file = new File(sourcePath);
        File[] files = file.listFiles();

        if (!new File(destPath).exists()) {
            boolean mkdir = new File(destPath).mkdir();
        }
        for (File f : files) {
            if (f.isDirectory()) {
                copyDir(f.getPath(), destPath + file.separator + f.getName());
            } else {
                copyFile(f.getPath(), destPath + file.separator + f.getName());
            }
        }
        return destPath;
    }

    //删除单个文件
    public boolean deleteOnlyFile(String fileName){
        File file = new File(fileName);
        if (file.exists() && file.isFile()){
            if (file.delete()){
                System.out.println("删除文件成功");
                return true;
            }else {
                System.out.println("删除文件失败");
                return false;
            }
        }
        System.out.println("删除文件失败,文件名称错误");
        return false;
    }

    //删除文件夹
    public boolean deleteDir(String dir){

        return true;
    }

    //删除文件（文件或者文件夹）
    public boolean deleteFile(String fileName){
        return true;
    }
    private String generateCoreZip() throws Exception {
        String corePath = generateCore("uuid", "");
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
//            ZipUtil zipUtil = new ZipUtil(logCoatRootZip, logCoatRoot);
//            zipUtil.zip();
//            String temp = logCoatRootZip+"_"+


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
