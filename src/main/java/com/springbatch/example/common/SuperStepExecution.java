package com.springbatch.example.common;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;

/**
 * Created by hongjong-wan on 2018. 1. 21..
 */
public class SuperStepExecution<T> {

    private StepExecution stepExecution;

    public void putData(String key, T player) {
        if (stepExecution == null) throw new NullPointerException("StepExecution is null");
        if (key == null || player == null) throw new NullPointerException();

        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        executionContext.put(key, player);
    }

    public T getData(String key) {
        if (stepExecution == null) throw new NullPointerException("StepExecution is null");
        if (key == null) throw new NullPointerException();

        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        return (T) executionContext.get(key);
    }

    protected void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
