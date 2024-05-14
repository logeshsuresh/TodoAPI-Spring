package com.example.TodoAPISpring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)")
    public void logTime(ProceedingJoinPoint joinPoint) {

        long startTime = System.currentTimeMillis(); // start time of the code

        // execute the join pont
        try {
            // execute the join point
            joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("Something went wrong during the execution");
        } finally {
            long endTime = System.currentTimeMillis();
            long totalExecutionTime = endTime - startTime;
            System.out.println("Total Time of Execution of the method is : " + totalExecutionTime + " ms");
        }
    }

}
