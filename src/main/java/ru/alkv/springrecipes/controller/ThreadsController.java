package ru.alkv.springrecipes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alkv.springrecipes.recipes.rcp_2_23_1.DemonstrationRunnable;
import ru.alkv.springrecipes.recipes.rcp_2_23_2.SpringExecutorsDemo;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;

@RestController
public class ThreadsController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationContext context;

    @GetMapping("/threads")
    public String threads() throws IOException {
        log.info("In the Threads controller!");

        String result = "<p>Threads ready!";
        try {
            Runnable task = new DemonstrationRunnable();

            // will create a pool of threads and attempt to reuse previously created ones if possible
            ExecutorService cachedThreadPoolExecutorService = Executors.newCachedThreadPool();

            result += "</p><p> The cachedThreadPoolExecutorService got task at " + new Date();
            //if (cachedThreadPoolExecutorService.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE))
            if (cachedThreadPoolExecutorService.submit(task).get() == null) {
                result += "</p><p> The cachedThreadPoolExecutorService has succeeded at " + new Date();
            }

            // Limits how many threads are created.
            // If all threads are processing tasks all other tasks will be waiting in queue
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);

            result += "</p><p> The fixedThreadPool got task at " + new Date();
            if (fixedThreadPool.submit(task).get() == null) {
                result += "</p><p> The fixedThreadPool has succeeded at " + new Date();
            }

            // It doesn't use more than one thread at a time
            ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();

            result += "</p><p> The singleThreadExecutorService got task at " + new Date();
            if (singleThreadExecutorService.submit(task).get() == null) {
                result += "</p><p> The singleThreadExecutorService has succeeded at " + new Date();
            }

            // Works like a TimerTask (starts task with specified delay)
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

            result += "</p><p> The scheduledExecutorService got task at " + new Date();
            if (scheduledExecutorService.schedule(task, 1, TimeUnit.SECONDS).get() == null) {
                result += "</p><p> The scheduledExecutorService has succeeded at " + new Date();
            }

            // Executes tasks until it encounters an exception, or it's cancelled
            scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            result = "<p>Interrupted exception: " + e.getMessage();
        }
        catch (ExecutionException e) {
            result = "<p>Execution exception: " + e.getMessage();
        }

        SpringExecutorsDemo springExecutorsDemo = context.getBean(SpringExecutorsDemo.class);
        springExecutorsDemo.submitJobs();

        log.info("Threads controller completed request");

        result += "</p>";

        return result;
    }
}
