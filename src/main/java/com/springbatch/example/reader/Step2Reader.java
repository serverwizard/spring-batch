package com.springbatch.example.reader;

import com.springbatch.example.common.SuperStepExecution;
import com.springbatch.example.domain.Player;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongjong-wan on 2018. 1. 20..
 */
@Component
//@StepScope
public class Step2Reader implements ItemReader<Player> {

    private List<Player> players = new ArrayList<>();

    boolean isRead = false;

    private int idx;


    @Override
    public Player read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (!isRead) {

            if (idx < players.size()) {
                return players.get(idx++);
            }

            isRead = true;
        }


        return null;
    }

    @BeforeStep
    public void init() {
        Player player1 = new Player("SF", "serverwizard", "developer", 30);
        Player player2 = new Player("SF", "seo", "PM", 30);
        Player player3 = new Player("SF", "lee", "developer", 30);
        Player player4 = new Player("SF", "suzhy", "developer", 30);
        Player player5 = new Player("SF", "kim", "developer", 20);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
    }

}
