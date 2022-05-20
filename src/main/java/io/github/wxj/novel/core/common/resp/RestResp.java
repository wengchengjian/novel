package io.github.wxj.novel.core.common.resp;

import io.github.wxj.novel.core.common.constants.ErrorCode;
import lombok.Data;

import java.util.Objects;

@Data
public class RestResp<T>{
    /**
     * 响应码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    private RestResp() {
        this.code = ErrorCode.OK.getCode();
        this.message = ErrorCode.OK.getMessage();
    }

    private RestResp(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    private RestResp(T data) {
        this();
        this.data = data;
    }

    /**
     * 业务处理成功,无数据返回
     */
    public static RestResp<Void> ok() {
        return new RestResp<>();
    }

    /**
     * 业务处理成功，有数据返回
     */
    public static <T> RestResp<T> ok(T data) {
        return new RestResp<>(data);
    }

    /**
     * 业务处理失败
     */
    public static RestResp<Void> fail(ErrorCode errorCode) {
        return new RestResp<>(errorCode);
    }

    /**
     * 系统错误
     */
    public static RestResp<Void> error() {
        return new RestResp<>(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 判断是否成功
     */
    public boolean isOk() {
        return Objects.equals(this.code, ErrorCode.OK.getCode());
    }
}
