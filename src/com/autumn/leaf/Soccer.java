package com.autumn.leaf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Soccer {

    private static Map<String, Integer> resultsTable = new HashMap<>();

    public static void main(String... args) {

        // String fileName = "c://victor/lines.txt";
        final String homeDir = System.getProperty("user.dir");// you can also
                                                              // use user.home
        final String fileName = homeDir + "/" + "lines.txt";
        List<String> list = new ArrayList<>();

        try (Stream<String> streams = Files.lines(Paths.get(fileName))) {
            List<String> strings = streams.collect(Collectors.toList());

            for (String string : strings) {

                if (string.isEmpty()) {

                    continue;

                }

                String[] teams = string.split(",");

                String teamOne = teams[0].trim();

                String teamOneName = teamOne.substring(0, teamOne.lastIndexOf(" "));

                Integer score1 = Integer.valueOf(teamOne.substring(teamOne.lastIndexOf(" ") + 1));

                String teamTwo = teams[1].trim();

                String teamTwoName = teamTwo.substring(0, teamTwo.lastIndexOf(" "));

                Integer score2 = Integer.valueOf(teamTwo.substring(teamTwo.lastIndexOf(" ") + 1));

                if (score1.equals(score2)) {
                    score1 = 0;
                    // draw

                    populateResultsTable(new Result(teamOneName, score1), new Result(teamTwoName, score2));

                } else if (score1 > score2) {
                    score1 = 3;
                    // team one wins

                    populateResultsTable(new Result(teamOneName, score1));

                } else if (score2 > score1) {
                    score2 = 3;
                    // team two wins

                    populateResultsTable(new Result(teamTwoName, score2));

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        resultsTable.keySet().stream().map(key -> {

            Integer value = resultsTable.get(key);

            return key + " " + value.toString();

        }).forEach(System.out::println);

    }

    private static void populateResultsTable(Result... result) {

        List<Result> results = Arrays.asList(result);

        results.stream().forEach(item -> {

            if (resultsTable.containsKey(item.getTeamName())) {

                Integer currentScore = resultsTable.get(item.getTeamName());

                resultsTable.put(item.getTeamName(), currentScore + item.getScore());

            } else {

                resultsTable.put(item.getTeamName(), item.getScore());

            }

        });

    }

}
