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
        System.out.println(fileService.installSh("", ""));
    }
}
