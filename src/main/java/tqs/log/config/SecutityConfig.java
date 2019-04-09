package tqs.log.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import tqs.log.security.LoginAuthFailHandler;
import tqs.log.security.LoginUrlEntryPoint;


@Configuration

//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)    //https://www.jianshu.com/p/41b7c3fb00e0
public class SecutityConfig extends WebSecurityConfigurerAdapter {


    /*
     * http权限控制
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //配置资源访问权限
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/user/login").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .failureHandler(authFailHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");

//我们成功登录后，spring security为了防止CSRF攻击，需要在每个页面中验证成功登录后创建的csrf token值，关闭验证
        http.csrf().disable();
//https://blog.csdn.net/YangFanJ/article/details/80858831
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public AuthenticationEntryPoint urlEntryPoint(){
        return new LoginUrlEntryPoint("/user/login");
    }

    @Bean
    public AuthenticationFailureHandler authFailHandler(){
        return new LoginAuthFailHandler((LoginUrlEntryPoint) urlEntryPoint());
    }
//    @Bean
//    public LoginSuccessHandle loginSuccessHandle

    /*
     * 自定义认证策略
     * */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {

//        基于内存的用户存储，认证
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and();

        //基于数据库的用户存储，认证

        //配置自定义的用户存储认证
//        auth.authenticationProvider(authProvider()).eraseCredentials(true);

    }

//    @Bean
//    public AuthProvider authProvider(){
//        return new AuthProvider();
//    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() {
//        AuthenticationManager authenticationManager = null;
//        try {
//            authenticationManager =  super.authenticationManager();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return authenticationManager;
//    }

/*
* java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
	at
* */

    //采用自定义md5加密方式
    @Bean
    public static PasswordEncoder passwordEncoder() {
//        return new MD5PasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }


    //添加md5加密
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
//
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return MD5Util.encode((String) rawPassword);
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.equals(MD5Util.encode((String) rawPassword));
//            }
//        }); //user Details Service验证
//    }

}
