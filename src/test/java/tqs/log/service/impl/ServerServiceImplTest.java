package tqs.log.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;
import tqs.log.dao.ServerMapper;
import tqs.log.entity.Server;

import java.util.List;

import static org.junit.Assert.*;

public class ServerServiceImplTest extends LogApplicationTests {

    @Autowired
    private ServerServiceImpl serverService;

    @Autowired
    private ServerMapper serverMapper;

    @Test
    public void createServer() {
    }

    @Test
    public void updateServer() {
    }

    @Test
    public void findById() {
        System.out.println(serverMapper.selectById(1));
    }

    @Test
    public void findByName(){
        List<Server> list = serverService.findByName("test");
        list.forEach(sever->{
            System.out.println(sever);
        });
    }

    @Test
    public void deleteById() {
    }
}