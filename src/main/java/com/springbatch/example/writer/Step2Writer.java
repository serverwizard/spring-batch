package com.springbatch.example.writer;

import com.springbatch.example.domain.Player;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hongjong-wan on 2018. 1. 20..
 */
@Component
public class Step2Writer implements ItemWriter<List<Player>> {
    @Override
    public void write(List<? extends List<Player>> items) throws Exception {

        items.forEach(player -> System.out.println(" 선수 소개 : " + player + "\n"));
    }
}
