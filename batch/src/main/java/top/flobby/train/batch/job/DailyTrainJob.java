package top.flobby.train.batch.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import top.flobby.train.batch.feign.BusinessFeign;
import top.flobby.train.common.resp.CommonResp;

import java.util.Date;

/**
 * @author : Flobby
 * @program : train
 * @description : 每日火车生成
 * @create : 2023-11-24 10:11
 **/

@DisallowConcurrentExecution
public class DailyTrainJob implements Job {
    public static final Logger LOGGER = LoggerFactory.getLogger(DailyTrainJob.class);

    @Resource
    private BusinessFeign businessFeign;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MDC.put("LOG_ID",System.currentTimeMillis()+ RandomUtil.randomString(3));
        LOGGER.info("每日火车生成任务开始 - 生成五天后的记录");
        Date today = new Date();
        DateTime offsetDay = DateUtil.offsetDay(today, 5);
        CommonResp<Object> commonResp = businessFeign.genDailyTrain(offsetDay.toJdkDate());
        LOGGER.info(commonResp.getMessage());
        LOGGER.info("每日火车生成任务结束 - 生成五天后的记录");
    }
}
