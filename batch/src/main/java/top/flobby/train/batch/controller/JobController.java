package top.flobby.train.batch.controller;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;
import top.flobby.train.batch.req.CronJobReq;
import top.flobby.train.batch.resp.CronJobResp;
import top.flobby.train.common.resp.CommonResp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Flobby
 * @program : train
 * @description : 定时任务控制API
 * @create : 2023-11-23 13:30
 **/

@Slf4j
@RestController
@RequestMapping("/admin/job")
public class JobController {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @PostMapping("/add")
    public CommonResp<Object> add(@RequestBody CronJobReq req) {
        String name = req.getName();
        String group = req.getGroup();
        String cronExpression = req.getCronExpression();
        String description = req.getDescription();
        log.info("新增定时任务：{},{},{},{}", name, group, cronExpression, description);

        try {
            // 通过 factory 获取一个调度器实例
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            // 启动
            scheduler.start();
            // 构建 job 信息
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(name)).withIdentity(name, group).build();
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 按新的 cronExpression 表达式构建一个新的 trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withDescription(description).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("创建定时任务结束");
            return CommonResp.success();
        } catch (SchedulerException e) {
            log.error("新增定时任务失败：{}", e.getMessage());
            return CommonResp.error("创建定时任务失败:调度异常");
        } catch (ClassNotFoundException e) {
            log.error("创建定时任务失败:" + e);
            return CommonResp.error("创建定时任务失败：任务类不存在");
        }
    }

    @PostMapping("/pause")
    public CommonResp<Object> pause(@RequestBody CronJobReq req) {
        String name = req.getName();
        String group = req.getGroup();
        log.info("暂停定时任务：{},{}", name, group);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败：{}", e.getMessage());
            return CommonResp.error("暂停定时任务失败：调度异常");
        }
        log.info("暂停定时任务结束");
        return CommonResp.success();
    }

    @PostMapping("/resume")
    public CommonResp<Object> resume(@RequestBody CronJobReq req) {
        String name = req.getName();
        String group = req.getGroup();
        log.info("重启定时任务：{},{}", name, group);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.resumeJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            log.error("重启定时任务失败：{}", e.getMessage());
            return CommonResp.error("恢复定时任务失败：调度异常");
        }
        log.info("重启定时任务结束");
        return CommonResp.success();
    }

    @PostMapping("/delete")
    public CommonResp<Object> delete(@RequestBody CronJobReq req) {
        String name = req.getName();
        String group = req.getGroup();
        log.info("删除定时任务：{},{}", name, group);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseTrigger(TriggerKey.triggerKey(name, group));
            scheduler.unscheduleJob(TriggerKey.triggerKey(name, group));
            scheduler.deleteJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败：{}", e.getMessage());
            return CommonResp.error("删除定时任务失败：调度异常");
        }
        log.info("删除定时任务结束");
        return CommonResp.success();
    }

    @PostMapping("/reschedule")
    public CommonResp<Object> reschedule(@RequestBody CronJobReq req) {
        String name = req.getName();
        String group = req.getGroup();
        String cronExpression = req.getCronExpression();
        String description = req.getDescription();
        log.info("更新定时任务：{},{},{},{}", name, group, cronExpression, description);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的 cronExpression 表达式重新构建 trigger
            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withDescription(description)
                    .withSchedule(scheduleBuilder)
                    .build();
            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务失败：{}", e.getMessage());
            return CommonResp.error("更新定时任务失败：调度异常");
        }
        log.info("更新定时任务结束");
        return CommonResp.success();
    }

    @GetMapping("/query")
    public CommonResp<List<CronJobResp>> query() {
        log.info("查看所有定时任务开始");
        List<CronJobResp> cronJobDtoList = new ArrayList<>();
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    CronJobResp cronJobResp = new CronJobResp();
                    cronJobResp.setName(jobKey.getName());
                    cronJobResp.setGroup(jobKey.getGroup());

                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    CronTrigger cronTrigger = (CronTrigger) triggers.get(0);
                    cronJobResp.setNextFireTime(cronTrigger.getNextFireTime());
                    cronJobResp.setPreFireTime(cronTrigger.getPreviousFireTime());
                    cronJobResp.setCronExpression(cronTrigger.getCronExpression());
                    cronJobResp.setDescription(cronTrigger.getDescription());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(cronTrigger.getKey());
                    cronJobResp.setState(triggerState.name());

                    cronJobDtoList.add(cronJobResp);
                }
            }
        } catch (SchedulerException e) {
            log.error("查看定时任务失败:" + e);
            return CommonResp.error("查看定时任务失败：调度异常");
        }
        log.info("查看定时任务结束");
        return CommonResp.success(cronJobDtoList);
    }
}
