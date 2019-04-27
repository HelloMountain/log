package tqs.log.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;
import tqs.log.model.PrivilegeModel;

import java.util.List;

import static org.junit.Assert.*;

public class PrivilegeServiceImplTest extends LogApplicationTests {

    @Autowired
    private PrivilegeServiceImpl privilegeService;

    @Test
    public void findAll() {
        List<PrivilegeModel> list = privilegeService.findAll();
        list.forEach(privilegeModel ->{
            System.out.println(privilegeModel.toString());
        } );
    }

    @Test
    public void findByUserId() {
    }

    @Test
    public void findByServerId() {
    }

    @Test
    public void deleteIds() {
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findByServername() {
    }
}
