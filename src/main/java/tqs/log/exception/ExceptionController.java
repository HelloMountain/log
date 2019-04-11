package tqs.log.exception;

import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tqs.log.base.ApiResponse;

/**
 * @Description 异常处理 controller
 */
@ControllerAdvice
public class ExceptionController {


    /**
     * 捕捉 CustomRealm 抛出的异常
     */
    @ResponseBody
    @ExceptionHandler(value = AccountException.class)
    public ApiResponse handleShiroException(Exception ex) {
        System.out.println("捕获到异常");
        return new ApiResponse().ofMessage(400, ex.getMessage());
    }

}
