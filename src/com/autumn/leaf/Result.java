package com.autumn.leaf;

public class Result {
    private String teamName;

    private Integer score;

    Result(String teamName, Integer score) {

        this.teamName = teamName;

        this.score = score;

    }

    public String getTeamName() {

        return teamName;

    }

    public Integer getScore() {

        return score;

    }

}
