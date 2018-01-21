package com.springbatch.example.processor;

import com.springbatch.example.common.SuperStepExecution;
import com.springbatch.example.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hongjong-wan on 2018. 1. 20..
 */
@Slf4j
@Component
//@StepScope
public class Step1Processor extends SuperStepExecution<Player> implements ItemProcessor<List<Player>, Player> {

    @Override
    public Player process(List<Player> item) throws Exception {

        // 방출할 맴버 셋팅
        super.putData("REMOVE_MEMBER", item.get(0));
        item.remove(0);

        sendGameScheduleToAllPlayers(item);
        return null;
    }

    private void sendGameScheduleToAllPlayers(List<Player> item) {
        item.forEach(player -> System.out.println(" [메일]선수 소개 : " + player + "\n"));
    }

    @BeforeStep
    public void setStepExecution(StepExecution stepExecution) {
        super.setStepExecution(stepExecution);
    }
}
