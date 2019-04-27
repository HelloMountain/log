package tqs.log.service;

import org.springframework.stereotype.Service;
import tqs.log.model.PrivilegeModel;
import tqs.log.model.request.PrivilegeRequest;

import java.util.List;

@Service
public interface PrivilegeService {

    List<PrivilegeModel> findAll();

    List<PrivilegeModel> findByUserId(int userId);

    List<PrivilegeModel> findByServerId(int serverId);

    int deleteIds(Integer[] ids);

    int add(PrivilegeRequest.Create create);

    int update(PrivilegeRequest.Update update);

    List<PrivilegeModel> findByUsername(String username);

    List<PrivilegeModel> findByServername(String servername);

}
