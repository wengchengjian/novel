package io.github.wxj.novel.core.common.exception;

import io.github.wxj.novel.core.common.constants.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException{
    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage(),null,false,false);
        this.errorCode = errorCode;
    }
}
