package com.springbatch.example.config;

import com.springbatch.example.domain.Player;
import com.springbatch.example.processor.Step1Processor;
import com.springbatch.example.processor.Step2Processor;
import com.springbatch.example.reader.Step1Reader;
import com.springbatch.example.reader.Step2Reader;
import com.springbatch.example.writer.Step2Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by hongjong-wan on 2017. 9. 13..
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final String JOB_NAME = "job";
    private static final String STEP1_NAME = "sendGameScheduleStep";
    private static final String STEP2_NAME = "removePlayerStep";
    private static final int CHUNK_SIZE = 3;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Step1Reader step1Reader;
    @Autowired
    private Step1Processor step1Processor;


    @Autowired
    private Step2Reader step2Reader;
    @Autowired
    private Step2Processor step2Processor;
    @Autowired
    private Step2Writer step2Writer;

    @Bean
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(sendGameScheduleStep()) // 1번째 step
                .next(removePlayerStep()) // 2번째 step
                .build();
    }

    // 방출 맴버를 제외한 모든 멤버들에게 게임 일정에 관한 메일 발송
    @Bean
    public Step sendGameScheduleStep() {
        return stepBuilderFactory.get(STEP1_NAME)
                .<List<Player>, Player>chunk(CHUNK_SIZE)
                .reader(step1Reader)
                .processor(step1Processor)
                .listener(promotionListener())
                .build();
    }


    // 방출 멤버를 제외한 맴버 등록
    @Bean
    public Step removePlayerStep() {
        return stepBuilderFactory.get(STEP2_NAME)
                .<Player, List<Player>>chunk(CHUNK_SIZE)
                .reader(step2Reader)
                .processor(step2Processor)
                .writer(step2Writer)
                .listener(promotionListener())
                .build();
    }

    @Bean
    public ExecutionContextPromotionListener promotionListener() {
        ExecutionContextPromotionListener executionContextPromotionListener = new ExecutionContextPromotionListener();
        // 데이터 공유를 위해서는 사용될 key를 미리 빈에 등록해줘야 한다.
        executionContextPromotionListener.setKeys(new String[]{"STAR_MEMBER"});

        return executionContextPromotionListener;
    }


}