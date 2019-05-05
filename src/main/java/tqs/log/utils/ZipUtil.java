package tqs.log.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ZipUtil {

    private String zipFileName;
    private String sourceFileName;

    public ZipUtil() {
    }

    public ZipUtil(String zipFileName, String sourceFileName) {
        this.zipFileName = zipFileName;
        this.sourceFileName = sourceFileName;
    }

    public void zip() throws Exception {


        FileOutputStream fileOutputStream = new FileOutputStream(new File(zipFileName));
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
        File sourceFile = new File(sourceFileName);
        compress(sourceFile, bufferedOutputStream, sourceFileName, zipOutputStream);

        bufferedOutputStream.close();
        zipOutputStream.close();
        fileOutputStream.close();
    }

    public void compress(File sourceFile, BufferedOutputStream bos, String base, ZipOutputStream zos) throws Exception {

        //如果是目录则对其中的每个文件进行压缩
        if (sourceFile.isDirectory()) {
            File[] filesList = sourceFile.listFiles();

//            assert filesList != null;
            //如果是一个空的目录
            if (Objects.requireNonNull(filesList).length == 0) {
                System.out.println(base + "/");
                zos.putNextEntry(new ZipEntry(base + "/"));
            } else {
                for (File f : filesList) {
                    compress(f, bos, base + "/" + f.getName(), zos);
                }
            }
        } else {
            zos.putNextEntry(new ZipEntry(base));
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);

            int tag;
            System.out.println(base);

            if ((tag = bis.read()) != -1) {
                bos.write(tag);
            }
            fileInputStream.close();
            bis.close();
        }
    }

    //对文件名进行压缩
    public void compressFile(File sourceFile, String zipFileName) throws Exception {
        //zipFileName  压缩后最外面的文件名
        FileOutputStream fileOutputStream = new FileOutputStream(new File(zipFileName));
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
        //new zipEntry("修改后的文件名")
        zipOutputStream.putNextEntry(new ZipEntry(sourceFile.getName()));
        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        int tag;
        while ((tag = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(tag);
        }

        //从外到里close
        bufferedInputStream.close();
        fileInputStream.close();
        bufferedOutputStream.close();
        zipOutputStream.close();
        fileOutputStream.close();
    }

    //供压缩的文件路径
    private static String logCoatRoot = "zipTest2"; // logCoat 的保存地址

    // 压缩后供下载的文件路径

    private static String logCoatRootZip = "C:\\Users\\qingshan\\Desktop\\flume.zip";

    public static void main(String[] args) {
        try {
//            ZipUtil zipUtil = new ZipUtil(logCoatRootZip, logCoatRoot);
            ZipUtil zipUtil = new ZipUtil("C:\\Users\\qingshan\\Desktop\\piczip.zip", "C:\\Users\\qingshan\\Desktop\\pic");
            zipUtil.zip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
