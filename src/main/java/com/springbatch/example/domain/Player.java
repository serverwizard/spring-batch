package com.springbatch.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by hongjong-wan on 2018. 1. 20..
 */
//@AllArgsConstructor
//@ToString
public class Player {

    private String team;
    private String name;
    private String position;
    private int age;

    public Player(String team, String name, String position, int age) {
        this.team = team;
        this.name = name;
        this.position = position;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (age != player.age) return false;
        if (!team.equals(player.team)) return false;
        if (!name.equals(player.name)) return false;
        return position.equals(player.position);
    }

    @Override
    public int hashCode() {
        int result = team.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "team='" + team + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", age=" + age +
                '}';
    }
}
