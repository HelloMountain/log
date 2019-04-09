package tqs.log.service;

import org.springframework.stereotype.Component;
import tqs.log.entity.User;

@Component
public interface UserService {
    User findUserByName(String name);
}
