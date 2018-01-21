package com.springbatch.example.reader;

import com.springbatch.example.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongjong-wan on 2018. 1. 20..
 */
@Slf4j
@Component
public class Step1Reader implements ItemReader<List<Player>> {

    private List<Player> players = new ArrayList<>();
    private boolean isRead;


    @Override
    public List<Player> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (!isRead) {
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

            isRead = true;

            return players;
        }

        // 더이상 읽어올 Item이 없으면 null 반환
        return null;
    }
}
