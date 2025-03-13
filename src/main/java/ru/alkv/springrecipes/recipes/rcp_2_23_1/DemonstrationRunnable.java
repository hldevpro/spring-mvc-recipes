package ru.alkv.springrecipes.recipes.rcp_2_23_1;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemonstrationRunnable implements Runnable {
    static int startsCount = 0;
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            System.out.printf("Starting execution of DemonstrationRunnable at %s \n", new Date());

            if (startsCount >= 520)
            {
                System.out.println(Thread.currentThread().getName());
                System.out.printf("Limit of execution reached at %s \n", new Date());

                throw new InterruptedException();
            }

            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName());
            System.out.printf("Completed execution of DemonstrationRunnable at %s \n", new Date());

            startsCount++;
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
        System.out.printf("DemonstrationRunnable finished at %s \n", new Date());
    }
}
