package org.ichat.backend.exeception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccountExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(AccountException.class)
    String accountErrorHandler(AccountException ex) {
        return ex.getMessage();
    }
}
