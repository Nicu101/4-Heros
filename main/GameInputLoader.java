package main;

import angels.Angel;
import characters.Hero;
import fileio.implementations.FileReader;
import map.Map;

import java.util.ArrayList;

public final class GameInputLoader {
    private final String inputPath;

    GameInputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public Game load() {

        try {
            int nrLinesMap;
            int nrColumnsMap;

            // Opening the file for writing
            FileReader fr = new FileReader(this.inputPath);

            nrLinesMap = fr.nextInt();
            nrColumnsMap = fr.nextInt();

            // Allocating the map
            Map[][] map = new Map[nrLinesMap][nrColumnsMap];

            // Reading the map
            for (int i = 0; i < nrLinesMap; i++) {
                String newLine = fr.nextWord();
                for (int j = 0; j < nrColumnsMap; j++) {
                    map[i][j] = Map.addMapType(newLine.charAt(j));
                }
            }

            // Reading the number of Heros
            int nrHeros;
            nrHeros = fr.nextInt();

            Hero[] heros = new Hero[nrHeros];
            int coordinateX;
            int coordinateY;
            String typeHero;
            HeroFactory heroFactory = new HeroFactory();
            StrategyFactory strategyFactory = new StrategyFactory();
            // Reading the heroType and their position
            for (int i = 0; i < nrHeros; i++) {
                typeHero = fr.nextWord();
                coordinateY = fr.nextInt();
                coordinateX = fr.nextInt();

                heros[i] = heroFactory.create(typeHero, coordinateX, coordinateY);
                // Setting the strategy for the hero
                heros[i].setStrategy(strategyFactory.create(typeHero));
                // Remembering the hero position in the list;
                heros[i].setPosInList(i);
            }

            // Reading the number of rounds;
            int nrRounds;
            nrRounds = fr.nextInt();

            // Allocating the moves
            Character[][] moves = new Character[nrRounds][nrHeros];

            // Reading the moves with every line representing the rounds
            for (int i = 0; i < nrRounds; i++) {
                String newLine = fr.nextWord();

                for (int j = 0; j < nrHeros; j++) {
                    moves[i][j] = newLine.charAt(j);
                }
            }

            // Reading the angels for every round

            // An array that knows how many angels are in every round
            int[] numbersAngelsPerRound = new int[nrRounds];
            // An array list that knows all the angels
            ArrayList<Angel> angels = new ArrayList<Angel>();
            // A Factory that can create Angels
            AngelFactory angelFactory = new AngelFactory();

            // Reading rounds lines
            for (int round = 0; round < nrRounds; round++) {
                // Reading the number of angel this round
                // At the same time, saving the number of angels until this round
                int nrAngels;
                if (round == 0) {
                    numbersAngelsPerRound[round] = fr.nextInt();
                    nrAngels = numbersAngelsPerRound[round];
                } else {
                    numbersAngelsPerRound[round] = fr.nextInt() + numbersAngelsPerRound[round - 1];
                    nrAngels = numbersAngelsPerRound[round] - numbersAngelsPerRound[round - 1];
                }

                // Reading all the angels in this round
                for (int i = 0; i < nrAngels; i++) {
                    // Separating the coordinates from the name
                    String[] values = fr.nextWord().split(",");
                    coordinateY = Integer.parseInt(values[1]);
                    coordinateX = Integer.parseInt(values[2]);

                    // Creating and adding a new Angel to the list
                    angels.add(angelFactory.create(values[0], coordinateX, coordinateY));
                }
            }

            // Closing the file
            fr.close();

            // Returning the information for a new game
            return new Game(moves, map, heros, nrRounds, nrHeros, angels, numbersAngelsPerRound);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
