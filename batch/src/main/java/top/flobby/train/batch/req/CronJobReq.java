package top.flobby.train.batch.req;

import lombok.Data;

/**
 * 定时任务入参类
 *
 * @author Flobby
 * @date 2023/11/23
 */
@Data
public class CronJobReq {
    private String group;

    private String name;

    private String description;

    private String cronExpression;
}