package com.bjtu.airport.exception.handler;


import  com.bjtu.airport.contant.Result;
import  com.bjtu.airport.contant.ResultCode;
import  com.bjtu.airport.exception.CustomException;
import  com.bjtu.airport.vo.ResultVo;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * @author: LiuYi
 * @date: 2019/4/18 14:59
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //使用mapp存放异常类型 和错误代码的映射，ImmutableMap的特点是已创建就不可变，并且线程安全
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    //是由builder来构建一个异常类型和错误代码的映射
    private static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder =
            ImmutableMap.builder();

    static {
        //初始化基础类型异常与错误代码的映射
        builder.put(NullPointerException.class, ResultCode.NULL);
        builder.put(SocketTimeoutException.class, ResultCode.TIMEOUT);
        builder.put(MissingServletRequestParameterException.class, ResultCode.MISS_REQUEST_PARAMETER);
        builder.put(MissingPathVariableException.class, ResultCode.MISS_PATH_VARIABLE);
    }


    //    shiro定义错误
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Result ShiroException(HttpServletRequest request, ShiroException e) {
//        log.error("catch exception: PATH: {}; CLASS: {}; MSG: {} \r\n", request.getRequestURI(), e.getClass(), e.getMessage());
        if (e instanceof UnauthorizedException) {
            return ResultVo.error(ResultCode.UNAUTHORISE);
        }
        return ResultVo.error(ResultCode.UNAUTHENTICATED);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customException(HttpServletRequest request, CustomException e) {
//        log.error("catch exception: PATH: {}; CLASS: {}; MSG: {} \r\n", request.getRequestURI(), e.getClass(), e.getMessage());
        return ResultVo.error(e.getResultCode());
    }

    /**
     * 处理请求对象属性不满足校验规则的异常信息
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public Result ValidException(HttpServletRequest request, Exception e) {
        log(request, e);
        BindingResult result = null;
        if (e instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            result = ((BindException) e).getBindingResult();
        }
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        fieldErrors.forEach(fieldError -> {
            builder.append(fieldError.getDefaultMessage() + "；");
        });
        return ResultVo.error(ResultCode.BAD_PATAMETER, builder.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(HttpServletRequest request, Exception e) {
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        final ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        if (resultCode != null) {
            log(request, e);
            return ResultVo.error(resultCode);
        } else {
            logDetial(request, e);
            return ResultVo.error(ResultCode.FAIL);
        }
    }

    private static void log(HttpServletRequest request, Exception e) {
//        log.error("Catch Exception: PATH: {}; CLASS: {}; MSG: {}", request.getRequestURI(), e.getClass(), e.getMessage());
    }

    private static void logDetial(HttpServletRequest request, Exception e) {
//        log.error("Catch Exception: PATH: {}; ", request.getRequestURI(), e);
    }
}