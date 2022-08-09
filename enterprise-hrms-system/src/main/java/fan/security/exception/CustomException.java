package fan.security.exception;

import org.springframework.security.core.AuthenticationException;

;

/**
 * @ClassName CustomException
 * @Description TODO
 * @Author Fan
 * @Date 2022/5/6 7:52
 * @Version 1.0
 */
public class CustomException extends AuthenticationException {

    public CustomException(String msg) {
        super(msg);
    }
}
