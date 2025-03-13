package ru.alkv.springrecipes.recipes.rcp_2_23_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.alkv.springrecipes.recipes.rcp_2_23_1.DemonstrationRunnable;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class SpringExecutorsDemo {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpleAsyncTaskExecutor asyncTaskExecutor;

    @Autowired
    private SyncTaskExecutor syncTaskExecutor;

    @Autowired
    private TaskExecutorAdapter taskExecutorAdapter;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private DemonstrationRunnable task;

    public void submitJobs() {
        System.out.println(Thread.currentThread().getName());
        System.out.printf("SyncTaskExecutor got job at %s \n", new Date());

        syncTaskExecutor.execute(task);

        System.out.println(Thread.currentThread().getName());
        System.out.printf("SyncTaskExecutor finished job at %s \n", new Date());

        System.out.println(Thread.currentThread().getName());
        System.out.printf("TaskExecutorAdapter got job at %s \n", new Date());

        taskExecutorAdapter.submit(task);

        System.out.println(Thread.currentThread().getName());
        System.out.printf("TaskExecutorAdapter finished job at %s \n", new Date());

        System.out.println(Thread.currentThread().getName());
        System.out.printf("SimpleAsyncTaskExecutor got job at %s \n", new Date());

        asyncTaskExecutor.submit(task);

        System.out.println(Thread.currentThread().getName());
        System.out.printf("SimpleAsyncTaskExecutor finished job at %s \n", new Date());

        System.out.println(Thread.currentThread().getName());
        System.out.printf("ThreadPoolTaskExecutor start submitting jobs at %s \n", new Date());

        // This will do 100 threds at a time, then queue the rest, ie,
        // should take round 5 seconds total
        for (int i = 0; i < 500; i++) {
            threadPoolTaskExecutor.submit(task);
        }

        System.out.println(Thread.currentThread().getName());
        System.out.printf("ThreadPoolTaskExecutor finished submitting all jobs at %s \n", new Date());
    }
}
