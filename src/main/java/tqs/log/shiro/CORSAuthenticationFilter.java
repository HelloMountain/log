package tqs.log.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class CORSAuthenticationFilter extends FormAuthenticationFilter {

    public CORSAuthenticationFilter() {
        super();
    }

/*
* 前后端分离项目中，由于跨域，会导致复杂请求，即会发送preflighted request，
* 这样会导致在GET／POST等请求之前会先发一个OPTIONS请求，但OPTIONS请求并
* 不带shiro的'Authorization'字段（shiro的Session），即OPTIONS请求不能通过shiro验证，会返回未认证的信息。
* 解决方法：给shiro增加一个过滤器，过滤OPTIONS请求
* */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONS
        System.out.println("111");
        System.out.println(((HttpServletRequest) request).getMethod().toUpperCase());
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
            return super.isAccessAllowed(request, response, mappedValue);
        }
}
