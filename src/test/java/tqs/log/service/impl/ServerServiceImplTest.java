package tqs.log.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;

import static org.junit.Assert.*;

public class ServerServiceImplTest extends LogApplicationTests {

    @Autowired
    private ServerServiceImpl serverService;

    @Test
    public void createServer() {
    }

    @Test
    public void updateServer() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void batchDelete() {
        Integer[] arrays = {3};
        serverService.batchDelete(arrays);
    }
}
