package tqs.log.service.impl;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.log.dao.PrivilegeMapper;
import tqs.log.entity.Privilege;
import tqs.log.model.PrivilegeModel;
import tqs.log.model.request.PrivilegeRequest;
import tqs.log.service.PrivilegeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PrivilegeModel> findAll() {
//        List<Privilege> list = privilegeMapper.selectList(null);

        List<PrivilegeModel> result = new ArrayList<>();
        result = privilegeMapper.findAll();
//        if (list != null && list.size() > 0){
//            list.forEach(privilege -> {
//                result.add(modelMapper.map(privilege, PrivilegeModel.class));
//            });
//        }
        return result;
    }

    @Override
    public List<PrivilegeModel> findByUserId(int userId) {
        List<Privilege> list = privilegeMapper.findByUserId(userId);
        List<PrivilegeModel> result = new ArrayList<>();
        if (list != null && list.size() > 0){
            list.forEach(privilege -> {
                result.add(modelMapper.map(privilege, PrivilegeModel.class));
            });
        }
        return result;
    }

    @Override
    public List<PrivilegeModel> findByServerId(int serverId) {
        List<Privilege> list = privilegeMapper.findByServerId(serverId);
        List<PrivilegeModel> result = new ArrayList<>();
        if (list != null && list.size() > 0){
            list.forEach(privilege -> {
                result.add(modelMapper.map(privilege, PrivilegeModel.class));
            });
        }
        return result;
    }

    @Override
    public int deleteIds(Integer[] ids) {
        int n = privilegeMapper.deleteBatchIds(Arrays.asList(ids));
        return n;
    }

    @Override
    public int add(PrivilegeRequest.Create create) {
        Privilege privilege = new Privilege();
        privilege.setUserId(create.getUserId());
        privilege.setNginxId(create.getNginxId());
        privilege.setCreated_at(new Date());
        privilege.setUpdated_at(new Date());
        int n = privilegeMapper.insert(privilege);
        return n;
    }

    @Override
    public int update(PrivilegeRequest.Update update) {
        Privilege privilege = privilegeMapper.selectById(update.getId());
        privilege.setUpdated_at(new Date());
        int n = privilegeMapper.updateById(privilege);
        return n;
    }

    @Override
    public List<PrivilegeModel> findByUsername(String username) {
        return null;
    }

    @Override
    public List<PrivilegeModel> findByServername(String servername) {
        return null;
    }
}
