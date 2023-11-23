package top.flobby.train.batch.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * test3
 *
 * @author Flobby
 * @date 2023/11/23
 */
@DisallowConcurrentExecution
public class Test3Job implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJob333333333 执行");
    }
}