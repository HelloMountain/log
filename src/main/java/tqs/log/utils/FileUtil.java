package tqs.log.utils;

import org.joda.time.DateTime;

import java.io.*;
import java.util.Random;

public class FileUtil {

    /*
    * 修改文件内容
    * 在网上提取到ip后尾部多余端口信息，去掉这些端口信息
    * */
    public static void alter(String filePath)throws Exception{

        File file = new File(filePath);

    }

    //读取文件
    public static String readFileContent(String filePath)throws Exception {
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        String destFilePath = "C:\\Users\\qingshan\\Desktop\\ip2.txt";

        bufferedReader = new BufferedReader(new FileReader(filePath));
        Random random = new Random();
        while ((temp = bufferedReader.readLine()) != null ){

//            stringBuffer.append(temp.replaceAll(":[0123456789]*",""));
//            stringBuffer.append(temp);
//            stringBuffer.append("\r\n");
            System.out.println(temp);
            int temp1 = random.nextInt(20) + 1;
            for (int i = 0; i < temp1; i++){
                writeFile(destFilePath, temp);
                writeFile(destFilePath, "\r\n");
            }
        }

//        if ((temp = bufferedReader.readLine()) != null){
//            String[] split = temp.split(" ");
//            for (int i = 0; i < split.length; i++) {
//                System.out.println(split[i]);
//            }
//        }
//        bufferedReader.close();
        return stringBuffer.toString();
    }

    //写入文件
    public static void writeFile(String filePath, String content) throws Exception{

        //后面默认为false  不是追加末尾，覆盖前面内容，
        //true，追加末尾
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    //读取gitub上的原始数据，创建nginx日志
    public static void writeLog(String filePath) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String temp = null;
        Random random = new Random();
        if ((temp = bufferedReader.readLine()) != null){
            System.out.println(temp);
//            int temp1 = random.nextInt(20) + 1;
//            for (int i = 0; i < temp1; i++){
//                writeFile("C:\\Users\\qingshan\\Desktop\\ip2.txt", temp);
//                writeFile("C:\\Users\\qingshan\\Desktop\\ip2.txt", "\r\n");
//            }
            String[] s = temp.split(" ");
            for (int i = 0; i < s.length; i++) {
                System.out.println(i + ":" + s[i]);
            }
        }
    }

    //创建timestamp
    public static void timestamp(){
        DateTime dateTime = new DateTime();
        DateTime time = new DateTime("2019-04-03T22:40:59+08:00");
        //推迟
        System.out.println(dateTime.plusSeconds(1));
        //提前
        System.out.println(dateTime.minusSeconds(1));

        for (int i = 0; i < 10; i++) {
            System.out.println(dateTime.plusSeconds(i));
        }
    }

    //创建5000条ip
    public static void createIp(){
        String filePath = "C:\\Users\\qingshan\\Desktop\\ip.txt";
        try {
            writeFile(filePath, readFileContent("C:\\Users\\qingshan\\Desktop\\test.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createData(String filePath, String filePath2) throws Exception{
        String temp1 = "{\"timestamp\":\"";
        String temp2 = "";//timestamp
        String temp3 = "\",\"version\":\"1\",\"client\":\"";
        String temp4 = "";//client  ip
        String temp5 = "\",\"url\":\"";
        String temp6 = "";//url
        String temp7 = "\",\"status\":\"";
        String temp8 = "";//code
        String temp9 = "\",\"domian\":\"192.168.137.122\",\"host\":\"api.shiguangxiaowu.cn\",\"size\":\"";
        String temp10 = "";//size
        String temp11 = "\",\"responsetime\":\"";
        String temp12 = "";//responsetime
        String temp13 = "\",\"referer\":\"-\",\"ua\":\"";
        String temp14 = "";//ua
        String temp15 = "\"}";


        //temp2
        DateTime dateTime = new DateTime();
        DateTime time = new DateTime("2019-05-18T22:40:59+08:00");
        //推迟
        System.out.println(dateTime.plusSeconds(1));
        //提前
        System.out.println(dateTime.minusSeconds(1));

        //
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(filePath2));
        String t = null;
        String t1 = null;
        String[] t3 = {"Chrome", "IE", "QQ", "IE 8.0", "2345", "IE 11.0", "搜狗", "Firefox"};

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            if ((t = bufferedReader.readLine()) != null &&(t1 = bufferedReader1.readLine()) != null ){
                String[] s = t.split(" | ");
//                if (i <= 4){
//
//                    for (int j = 0; j < s.length; j++) {
//                        System.out.println(j + ":" + s[j]);
//                    }
//                }

//            System.out.println(temp);
                temp2 = dateTime.minusSeconds(i).toString();
                temp6 = s[8];
                temp8 = s[13];
                temp10 = s[15];
                temp12 = s[s.length - 1];
                temp4 = t1;
                int index = random.nextInt(7);//[0,n)
                temp14 = t3[index] + "/";
//                temp14 = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36";
                String temp = temp1 + temp2 + temp3 + temp4 + temp5 + temp6 + temp7 + temp8 + temp9 + temp10
                        + temp11 + temp12 + temp13 + temp14 + temp15;
                System.out.println(temp);
                writeFile("C:\\Users\\qingshan\\Desktop\\log.txt", temp);
                writeFile("C:\\Users\\qingshan\\Desktop\\log.txt", "\r\n");
            }
        }
    }
    public static void main(String[] args) {
//        String filePath = "C:\\Users\\qingshan\\Desktop\\ip2.txt";
//        try {
////            writeFile(filePath, readFileContent("C:\\Users\\qingshan\\Desktop\\test.txt"));
//            readFileContent("C:\\Users\\qingshan\\Desktop\\test.txt");//C:\Users\qingshan\Downloads\nginx_log-master\nginx_log
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            writeLog("C:\\Users\\qingshan\\Downloads\\nginx_log-master\\nginx_log\\nginx.log");//C:\Users\qingshan\Downloads\nginx_log-master\nginx_log\nginx.log
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        timestamp();

        FileUtil fileUtil = new FileUtil();
        try {
            fileUtil.createData("C:\\Users\\qingshan\\Downloads\\nginx_log-master\\nginx_log\\nginx.log", "C:\\Users\\qingshan\\Desktop\\ip2.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
