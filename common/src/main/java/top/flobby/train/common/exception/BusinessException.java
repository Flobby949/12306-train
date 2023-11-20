package top.flobby.train.common.exception;

import lombok.Getter;

/**
 * @author : Flobby
 * @program : train
 * @description : 自定义业务异常
 * @create : 2023-11-20 16:15
 **/

@Getter
public class BusinessException extends RuntimeException{

    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 不写入堆栈信息，提高性能
     *
     * @return {@link Throwable}
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
