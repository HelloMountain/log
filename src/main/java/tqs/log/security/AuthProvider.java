package tqs.log.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import tqs.log.entity.User;
import tqs.log.service.UserService;

//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/*
* 自定义认证实现
* */
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;


    /*
    * spring-boot-starter-parent 2以上没有这个类
    * */
//    @Autowired
//    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassword = (String)authentication.getCredentials();

        User user = userService.findUserByName(userName);
        if (user == null){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
//        if (this.passwordEncoder.isPasswordValid(user.getPassword(), inputPassword, user.getId())){
//            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//        }
        if (user.getPassword().equals(inputPassword)){
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }

        throw new BadCredentialsException("authError");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
