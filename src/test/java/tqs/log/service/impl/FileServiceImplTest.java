package tqs.log.service.impl;

import io.swagger.annotations.Api;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;

import static org.junit.Assert.*;

public class FileServiceImplTest extends LogApplicationTests {

    @Autowired
    private FileServiceImpl fileService;
    @Test
    public void installSh() {
        System.out.println(fileService.installSh(""));
    }

    @Test
    public void generateCore(){
        fileService.generateCore("2", "");
    }

    @Test
    public void copyDir(){
        try {
            fileService.copyDir("C:\\Users\\qingshan\\Downloads\\apache-flume-1.8.0-bin.tar\\apache-flume-1.8.0-bin\\conf","C:\\Users\\qingshan\\Desktop\\conf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteOnlyFile(){
        fileService.deleteOnlyFile("C:\\Users\\qingshan\\Desktop\\1.txt");
    }
}
