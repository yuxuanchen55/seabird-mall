package com.yxc.seabirdmall.product.exception;

import com.yxc.seabirdmall.common.exception.SeabirdMallEnum;
import com.yxc.seabirdmall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller层 统一异常处理
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.yxc.seabirdmall.product.controller")
public class SeabirdmallExceptionalControllerAdvice {
    // 如果能够精准匹配到该异常就会执行这个方法，后续执行下面的方法
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handlerValidException(MethodArgumentNotValidException e) {
        log.error("参数校验异常：{}, 异常类型{}", e.getMessage(), e.getCause());

        Map<String, String> map = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(item -> {
            map.put(item.getField(), item.getDefaultMessage());
        });
        return R.error(SeabirdMallEnum.VALID_EXCEPTION.getCode(), SeabirdMallEnum.VALID_EXCEPTION.getMsg()).put("data", map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleValidException(Throwable throwable) {
        log.error("错误", throwable);
        return R.error(SeabirdMallEnum.UNKNOWN_EXCEPTION.getCode(), SeabirdMallEnum.UNKNOWN_EXCEPTION.getMsg()).put("data", "未知异常");
    }
}
