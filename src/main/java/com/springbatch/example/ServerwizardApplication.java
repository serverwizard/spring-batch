package com.springbatch.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Date;

/*
    To do List
    1. @Bean으로 등록하는 것과 @Component 어노테이션으로 등록하는 것의 차이?
    2. @StepScope, @PostConstruct
    3. ItemReader, ItemProcessor, ItemWrite작업으로 명세하거나,
        Tasklet 방식으로 배치 작업에 대한 명세 차이

 */
@SpringBootApplication
public class ServerwizardApplication {

    private static String[] JOB_LIST = {"job"};


    public static void main(String[] args) throws Exception {

        if (args.length <  1) {
            throw new IllegalArgumentException("No Argument");
        }

        String job = args[0];

        if (!Arrays.asList(JOB_LIST).contains(job)) {
            throw new IllegalArgumentException("Illegal Argument");
        }


        SpringApplication app = new SpringApplication(ServerwizardApplication.class);

        ConfigurableApplicationContext ctx = app.run(args);

        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

        Job newJob = ctx.getBean(job, Job.class);

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
//                .addParameter(job, new JobParameter(job))
                .toJobParameters();


        // Job 시작
        jobLauncher.run(newJob, jobParameters);

        System.exit(0);
    }
}