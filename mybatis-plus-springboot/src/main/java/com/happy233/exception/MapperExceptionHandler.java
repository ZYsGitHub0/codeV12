package com.happy233.exception;

import com.happy233.exception.mapper.UserNoFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Mapper的异常处理
 *
 * @author zy136
 * @date 2021/12/19 15:24
 */
@Component
@ControllerAdvice//异常管理
@Slf4j
public class MapperExceptionHandler {

    @ExceptionHandler(UserNoFoundException.class)
    public ModelAndView userNoFoundException(UserNoFoundException exception) {
        ModelAndView modelAndView = new ModelAndView();
        log.error(exception.getMessage());
        modelAndView.setViewName("forward:/error.html");
        return modelAndView;
    }
}
