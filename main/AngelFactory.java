package main;

import angels.Angel;
import angels.DamageAngel;
import angels.DarkAngel;
import angels.Dracula;
import angels.GoodBoy;
import angels.LevelUpAngel;
import angels.LifeGiver;
import angels.SmallAngel;
import angels.Spawner;
import angels.TheDoomer;
import angels.XPAngel;

public class AngelFactory {
    private final String damageAngel = "DamageAngel";
    private final String darkAngel = "DarkAngel";
    private final String draculaAngel = "Dracula";
    private final String goodBoyAngel = "GoodBoy";
    private final String levelUpAngel = "LevelUpAngel";
    private final String lifeGiverAngel = "LifeGiver";
    private final String smallAngel = "SmallAngel";
    private final String spawnerAngel = "Spawner";
    private final String theDoomerAngel = "TheDoomer";
    //private final String xpAngel = "XPAngel";

    /**
     * @param typeAngel - the type of angel that needs to be created.
     * @param coordinateX - Ox angel spawn coordinate.
     * @param coordinateY - Oy angel spawn coordinate.
     * @return A new Angel at the coordinates given and of the type received.
     */
    public Angel create(final String typeAngel, final int coordinateX, final int coordinateY) {
        if (typeAngel.equals(this.damageAngel)) {
            /* Creating a new DamageAngel */
            return new DamageAngel(coordinateX, coordinateY);
        } else if (typeAngel.equals(this.darkAngel)) {
            /* Creating a new DarkAngel */
            return new DarkAngel(coordinateX, coordinateY);
        } else if (typeAngel.equals(this.draculaAngel)) {
            /* Creating a new Dracula */
            return new Dracula(coordinateX, coordinateY);
        } else if (typeAngel.equals(this.goodBoyAngel)) {
            /* Creating a new GoodBoy */
            return new GoodBoy(coordinateX, coordinateY);
        } else if (typeAngel.equals(this.levelUpAngel)) {
            /* Creating a new LevelUpAngel */
            return new LevelUpAngel(coordinateX, coordinateY);
        } else if (typeAngel.equals(this.lifeGiverAngel)) {
            /* Creating a new LifeGiver */
            return new LifeGiver(coordinateX, coordinateY);
        } else if (typeAngel.equals(this.smallAngel)) {
            /* Creating a new SmallAngel */
            return new SmallAngel(coordinateX, coordinateY);
        } else if (typeAngel.equals(this.spawnerAngel)) {
            /* Creating a new Spawner */
            return new Spawner(coordinateX, coordinateY);
        } else if (typeAngel.equals(this.theDoomerAngel)) {
            /* Creating a new TheDoomer */
            return new TheDoomer(coordinateX, coordinateY);
        } else {
            /* Creating a new XPAngel */
            return new XPAngel(coordinateX, coordinateY);
        }
    }
}
