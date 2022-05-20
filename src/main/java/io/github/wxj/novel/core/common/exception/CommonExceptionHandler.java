package io.github.wxj.novel.core.common.exception;

import io.github.wxj.novel.core.common.constants.ErrorCode;
import io.github.wxj.novel.core.common.resp.RestResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     *  处理数据绑定异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public RestResp<Void> handleBindException(BindException e){
        log.error(e.getMessage(),e);
        return RestResp.fail(ErrorCode.USER_REQUEST_PARAM_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public RestResp<Void> handleBusinessException(BusinessException e){
        log.error(e.getMessage(),e);
        return RestResp.fail(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public RestResp<Void> handleSystemException(Exception e){
        log.error(e.getMessage(),e);
        return RestResp.error();
    }



}
