package main;

import angels.Angel;
import characters.Hero;
import map.Map;

import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
    private final int baseRewardExp = 200;
    private final int bonusRewardExp = 40;

    private final String spawnerId = "Spawner";

    private int[] numbersAngelsPerRound;
    private ArrayList<Angel> angels;
    private Character[][] moves;
    private Map[][] map;
    private Hero[] heros;
    private int nrRounds;
    private int nrHeros;

    Game(final Character[][] moves, final Map[][] map, final Hero[] heros,
         final int nrRounds, final int nrPlayers,
         final ArrayList<Angel> angels, final int[] numberAngelsPerRound) {
        this.moves = moves;
        this.map = map;
        this.heros = heros;
        this.nrRounds = nrRounds;
        this.nrHeros = nrPlayers;
        this.angels = angels;
        this.numbersAngelsPerRound = numberAngelsPerRound;
    }

    public final int getNumbersAngelsPerRound(final int round) {
        if (round == 0) {
            return numbersAngelsPerRound[round];
        }

        return numbersAngelsPerRound[round] - numbersAngelsPerRound[round - 1];
    }

    public final int numberOfAngelsUntilThisRound(final int round) {
        return numbersAngelsPerRound[round];
    }

    public final Angel getAngel(final int round, final int pos) {
        if (round == 0) {
            return angels.get(pos);
        }
        return angels.get(this.numberOfAngelsUntilThisRound(round - 1) + pos);
    }

    public final Character[][] getMoves() {
        return moves;
    }

    public final Character getMove(final int round, final int hero) {
        return this.getMoves()[round][hero];
    }

    public final Map[][] getMap() {
        return map;
    }

    public final Map getTypeMap(final int coordinateX, final int coordinateY) {
        return this.getMap()[coordinateY][coordinateX];
    }

    public final Hero[] getHeros() {
        return heros;
    }

    public final Hero getHero(final int posHero) {
        return this.getHeros()[posHero];
    }

    public final int getNrRounds() {
        return nrRounds;
    }

    public final int getNrHeros() {
        return nrHeros;
    }

    public final void applyAngelEffect(final Angel angel) {
        /* Looking to see if we have heros in the same position on the map */

        for (int heroNr = 0; heroNr < this.getNrHeros(); heroNr++) {
            Hero hero = this.getHero(heroNr);

            /* Checking to see if they are on the same position on the map */
            if ((hero.getCoordinateX() == angel.getCoordinateX())
                    && (hero.getCoordinateY() == angel.getCoordinateY())) {
                /* Applying the effect */
                /* Using a single dispatch to find what type of hero it is */
                hero.dispatchAngelEffect(angel);
            }
        }
    }

    public final boolean findSpawner(final Hero hero, final int round) {
        /* Looking to see if the is a spawner at his position */
        for (int angelNr = 0; angelNr < this.getNumbersAngelsPerRound(round); angelNr++) {
            Angel angel = this.getAngel(round, angelNr);

            if (angel.getTypeAngel().equals(this.spawnerId)) {
                /* Found a spawner */
                if ((angel.getCoordinateX() == hero.getCoordinateX())
                    && (angel.getCoordinateY() == hero.getCoordinateY())) {
                    /* At the hero position */
                    return true;
                }
            }
        }

        /* No Spawner angel found at the hero position */
        return false;
    }

    public final void rewardExp(final Hero hero1, final Hero hero2) {
        /* Saving the old hp */
        int oldHp1 = hero1.getCurrentHP();
        int oldHp2 = hero2.getCurrentHP();
        /* Saving hero1 level for calculating hero2 exp if necessary */
        int oldLevel1 = hero1.getLvl();

        int newExp;
        /* Calculate Hero1 Exp */
        newExp = hero1.getExp();
        newExp += Math.max(0, this.baseRewardExp
                - (hero1.getLvl() - hero2.getLvl()) * this.bonusRewardExp);
        /* Set the new Exp for the Hero1 */
        hero1.setExp(newExp);

        /* Calculate Hero2 Exp */
        newExp = hero2.getExp();
        newExp += Math.max(0, this.baseRewardExp
                - (hero2.getLvl() - hero1.getLvl()) * this.bonusRewardExp);
        /* Set the new Exp for the Hero2 */
        hero2.setExp(newExp);

        /* Checking to see if the hero1 leveled up */
        if (hero1.getExp() >= hero1.getNecessaryExpToLevelUP()) {
            /* Level Up the hero1 */
            hero1.levelUp();
            /* The hero is still dead */
            hero1.setCurrentHP(oldHp1);
        }

        /* Checking to see if the hero2 leveled up */
        if (hero2.getExp() >= hero2.getNecessaryExpToLevelUP()) {
            /* Temporally setting the hero1 level back for the Exp calculation */
            int newLevel1 = hero1.getLvl();
            hero1.setLvl(oldLevel1);

            /* Level Up the hero2 */
            hero2.levelUp();
            /* The hero is still dead */
            hero2.setCurrentHP(oldHp2);

            /* Setting back the true level for hero1 */
            hero1.setLvl(newLevel1);
        }
    }

    public final Hero findSecondHero(final Hero firstHero, final int heroPos) {
        /* Looking to see if we have a hero in the same position on the map */

        for (int hero = 0; hero < this.getNrHeros(); hero++) {
            /* Skipping/Ignoring the same hero */
            if (hero == heroPos) {
                continue;
            }

            Hero combatant = this.getHero(hero);
            /* Skipping/Ignoring dead heros*/
            if (combatant.getCurrentHP() <= 0) {
                continue;
            }

            /* Skipping/Ignoring if this hero fought this round */
            if (combatant.getFoughtThisRound()) {
                continue;
            }

            /* Checking to see if they are on the same position on the map */
            if ((combatant.getCoordinateX() == firstHero.getCoordinateX())
                    && (combatant.getCoordinateY() == firstHero.getCoordinateY())) {
                return combatant;
            }
        }

        /* No hero found in the same position on the map */
        return null;
    }

    public final void rewardExpToWinner(final Hero winner, final Hero loser, final int round) {
        /* Checking to see if there is a Spawner angel at his position this round */
        boolean foundSpawner = this.findSpawner(winner, round);

        /* Check to see if the winner is alive and for a Spawner */
        if ((winner.getCurrentHP() <= 0) && !foundSpawner) {
            return;
        }

        /* Calculate winner Exp*/
        int expWinner = winner.getExp();
        expWinner += Math.max(0, this.baseRewardExp
                - (winner.getLvl() - loser.getLvl()) * this.bonusRewardExp);

        /* Set the new exp for the winner*/
        winner.setExp(expWinner);

        /* Checking to see if the hero leveled up */
        if (winner.getExp() >= winner.getNecessaryExpToLevelUP()) {
            /* Level Up the winner */
            winner.levelUp();
        }
    }

    public final void fight(final Hero firstHero, final Hero secondHero, final int round) {
        /* Before a fight looking to apply a strategie */
        firstHero.applyStrategy();
        secondHero.applyStrategy();

        /* Getting the type of map the heros fight on */
        Map zone = this.getTypeMap(firstHero.getCoordinateX(), firstHero.getCoordinateY());

        /* Calculating the first hero damage */
        int firstHeroDamage = firstHero.calculateDamage(secondHero, zone);

        /* Calculating the second hero damage */
        int secondHeroDamage = secondHero.calculateDamage(firstHero, zone);

        /* The heros take damage */
        firstHero.takeDamage(secondHeroDamage);
        secondHero.takeDamage(firstHeroDamage);

        /* The first hero won(killed the second hero) */
        if (secondHero.getCurrentHP() <= 0) {
            /* Notifying the Great Wizard about a player death */
            this.updateHeroStatus(secondHero, firstHero);

            /* Rewarding Exp*/
            rewardExpToWinner(firstHero, secondHero, round);
        }

        /* The second hero won(killed the first hero) */
        if (firstHero.getCurrentHP() <= 0) {
            /* Notifying the Great Wizard about a player death */
            this.updateHeroStatus(firstHero, secondHero);

            /* Rewarding Exp*/
            rewardExpToWinner(secondHero, firstHero, round);
        }

        /* If both of the heros died at the same time */
        if ((secondHero.getCurrentHP() <= 0) && (firstHero.getCurrentHP() <= 0)) {
            if (this.findSpawner(firstHero, round)) {
                /* No level up rewards if there is a Spawner */
                return;
            }
            /* Level up both of them */
            this.rewardExp(firstHero, secondHero);
        }

        /* Nobody won, nothing is happening :) */
    }

    public final void playGame() {
        int round;
        int heroPos;

        for (round = 0; round < this.getNrRounds(); round++) {
            /* This represent 1 round*/

            /* Notifying the Great Wizard about the start of a round */
            /* There is no Round 0 so i add 1 :))*/
            this.updateTheGreatWizardAboutTheRound(round + 1);

            /* Moving the heros */
            for (heroPos = 0; heroPos < this.getNrHeros(); heroPos++) {
                Hero hero = this.getHero(heroPos);

                /* Ignore dead hero */
                if (hero.getCurrentHP() <= 0) {
                    continue;
                }

                /* Try to move the hero */
                hero.moveHero(this.getMove(round, heroPos));

                /* The hero did not fight yet*/
                hero.setFoughtThisRound(false);
            }

            /* Looking to see if there are 2 heros on the same position */
            for (heroPos = 0; heroPos < this.getNrHeros(); heroPos++) {
                Hero firstHero = this.getHero(heroPos);

                /* Skipping/Ignoring dead heros*/
                if (firstHero.getCurrentHP() <= 0) {
                    continue;
                }

                /* Check to see if the hero already fought */
                /* If yes, this hero is skipped(ignored) */
                if (firstHero.getFoughtThisRound()) {
                    continue;
                }

                /* The Hero did not fight */
                /* Checking if 2 heros(fighters) are in the same spot on the map */
                Hero secondHero = this.findSecondHero(firstHero, heroPos);

                /* If no second hero is found at first hero coordinates on the map */
                /* This hero is skipped/ignored */
                if (secondHero == null) {
                    continue;
                }

                /* Found a second hero at the same coordinates */
                /* The 2 heros fight */
                this.fight(firstHero, secondHero, round);

                /* The 2 heros fought*/
                firstHero.setFoughtThisRound(true);
                secondHero.setFoughtThisRound(true);
            }

            /* Spawning the angels */
            for (int angelPos = 0; angelPos < this.getNumbersAngelsPerRound(round); angelPos++) {
                Angel angel = this.getAngel(round, angelPos);

                /* Notifying the Great Wizard about the angels spawning */
                this.updateTheGreatWizardAboutAngelsPositions(angel);

                /* Applying angel effect if there are heros at his position */
                this.applyAngelEffect(angel);
            }

            /* Notifying the Great Wizard about the end of the round */
            this.updateTheGreatWizardAboutTheRound();
        }
    }

    public final void gameResults(final String outputPath) {
        /* Writing to a file the result of the game*/

        try {

            /* Opening the file for writing */
            FileWriter fileWriter = new FileWriter(outputPath, true);

            /* Specifying what this is */
            fileWriter.write("~~ Results ~~\n");
            /* Going through the list of heros */
            for (int heroPos = 0; heroPos < this.getNrHeros(); heroPos++) {
                /* Writing to the file information about every hero */
                fileWriter.write(this.getHero(heroPos).toString());
                if (heroPos != this.getNrHeros() - 1) {
                    fileWriter.write("\n");
                }
            }

            /* Closing the file */
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Notifying the Great Wizard about the end of a round.
     */
    public void updateTheGreatWizardAboutTheRound() {
        /* Notifying the Great Wizard about the end of the round by send a empty line */
        this.setChanged();
        this.notifyObservers("\n");
    }

    /**
     * @param round - the present round that the game is on.
     * Notifying the Great Wizard about the starting of a new round.
     */
    public void updateTheGreatWizardAboutTheRound(final int round) {
        /* Create a message to indicate a new round */
        StringBuilder str = new StringBuilder();

        str.append("~~ Round ");
        str.append(round);
        str.append(" ~~\n");

        /* Notify the Great Wizard */
        this.setChanged();
        this.notifyObservers(str.toString());

    }

    /**
     * @param angel - the Angel that barely spawned.
     * Notifying the Great Wizard about a new Angel.
     */
    public void updateTheGreatWizardAboutAngelsPositions(final Angel angel) {
        /* Create a message about the position of the new angel spawned */
        StringBuilder str = new StringBuilder("Angel ");

        str.append(angel.getTypeAngel());
        str.append(" was spawned at ");
        str.append(angel.getCoordinateY());
        str.append(" ");
        str.append(angel.getCoordinateX());
        str.append("\n");

        /* Notify the Great Wizard */
        this.setChanged();
        this.notifyObservers(str.toString());
    }

    /**
     * @param killedHero - the hero who was killed.
     * @param killerHero - the hero that killed.
     * Notifying the Great Wizard about the death of a player..
     */
    public void updateHeroStatus(final Hero killedHero, final Hero killerHero) {
        /* Create a message that explains who killed 1 player */
        StringBuilder str = new StringBuilder("Player ");

        str.append(killedHero.getHeroType());
        str.append(" ");
        str.append(killedHero.getPosInList());
        str.append(" was killed by ");
        str.append(killerHero.getHeroType());
        str.append(" ");
        str.append(killerHero.getPosInList());
        str.append("\n");

        /* Notify the Great Wizard */
        this.setChanged();
        this.notifyObservers(str.toString());
    }

    /**
     * @param greatWizard - the observer object, named from now on the Great Wizard.
     * Adding the Great Wizard as the observer for the game, heros and angels.
     */
    public void addObservers(final TheGreatWizard greatWizard) {
        /* Adding the Great Wizard as observer */
        /* Add the game itself as observer */
        this.addObserver(greatWizard);

        /* The Great Wizard observe the players so that he know when they level up */
        for (int heroPos = 0; heroPos < this.getNrHeros(); heroPos++) {
            Hero hero = this.getHero(heroPos);
            hero.addObserver(greatWizard);
        }

        /* The Great Wizard observe the angel so that he know what did they do */
        for (int round = 0; round < this.getNrRounds(); round++) {
            for (int angelPos = 0; angelPos < this.getNumbersAngelsPerRound(round); angelPos++) {
                Angel angel = this.getAngel(round, angelPos);

                angel.addObserver(greatWizard);
            }
        }
    }
}
