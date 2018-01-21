package com.springbatch.example.processor;

import com.springbatch.example.common.SuperStepExecution;
import com.springbatch.example.domain.Player;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongjong-wan on 2018. 1. 20..
 */
@Component
public class Step2Processor extends SuperStepExecution<Player> implements ItemProcessor<Player, List<Player>> {

    private Player removePlayer;

    private List<Player> players = new ArrayList<>();

    @Override
    public List<Player> process(Player item) throws Exception {


        System.out.println(" 방출 선수 : " + removePlayer + "\n");

//       item.remove(removePlayer);

        players.add(item);

        return players;

    }

    @BeforeStep
    public void getExecutionContext(StepExecution stepExecution) {
        super.setStepExecution(stepExecution);
        removePlayer = super.getData("REMOVE_MEMBER");
    }
}
