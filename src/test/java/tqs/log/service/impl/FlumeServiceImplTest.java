package tqs.log.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;


public class FlumeServiceImplTest extends LogApplicationTests {

    @Autowired
    private FlumeServiceImpl flumeService;

    @Test
    public void generateFile(){
//        flumeService.generateFile("", "test", "aaaa");
    }

    @Test
    public void generateFlumeProperties(){
//        System.out.println(flumeService.flumeProperties());
    }
}