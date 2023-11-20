package top.flobby.train.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.flobby.train.common.resp.CommonResp;

/**
 * @author : Flobby
 * @program : train
 * @description : 统一异常处理
 * @create : 2023-11-20 16:10
 **/

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有异常的处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResp<?> exception(Exception e) {
        logger.error("系统异常：", e);
        CommonResp<?> commonResp = new CommonResp<>();
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getMessage());
        return commonResp;
    }

}
