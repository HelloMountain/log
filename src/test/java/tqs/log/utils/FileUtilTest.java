package tqs.log.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;

import static org.junit.Assert.*;

public class FileUtilTest extends LogApplicationTests {

    @Autowired
    private FileUtil fileUtil;

    @Test
    public void alter() {
    }

    @Test
    public void readFileContent() {
    }

    @Test
    public void writeFile() {
    }

    @Test
    public void writeLog() {
    }

    @Test
    public void timestamp() {
    }

    @Test
    public void createIp() {
    }

    @Test
    public void createData() {
        try {
            fileUtil.createData("C:\\Users\\qingshan\\Downloads\\nginx_log-master\\nginx_log\\nginx.log"
                    , "C:\\Users\\qingshan\\Desktop\\ip2.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createBrowser() {
        fileUtil.createBrowser();
    }
}
