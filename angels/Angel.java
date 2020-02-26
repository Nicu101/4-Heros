package angels;

import characters.Hero;
import characters.Knight;
import characters.Pyromancer;
import characters.Rogue;
import characters.Wizard;

import java.util.Observable;

public abstract  class Angel extends Observable {
    /* Coordinates */
    private int coordinateX;
    private int coordinateY;

    public abstract String getTypeAngel();

    public abstract String getEffect();

    /**
     * @return the Ox coordinate for this angel.
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * @return the Oy coordinate for this angel.
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    Angel(final int coordinateX, final int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /* An angel descends and affects a hero */
    /**
     * @param knight - the hero on who the effect is applied; can only be of type Knight.
     * @doing - applying the angel effect on the hero.
     */
    public abstract void angelEffect(Knight knight);

    /**
     * @param rogue - the hero on who the effect is applied; can only be of type Rogue.
     * @doing - applying the angel effect on the hero.
     */
    public abstract void angelEffect(Rogue rogue);

    /**
     * @param pyro - the hero on who the effect is applied; can only be of type Pyromancer.
     * @doing - applying the angel effect on the hero.
     */
    public abstract void angelEffect(Pyromancer pyro);

    /**
     * @param wizard - the hero on who the effect is applied; can only be of type Wizard.
     * @doing - applying the angel effect on the hero.
     */
    public abstract void angelEffect(Wizard wizard);

    /**
     * @param hero - the hero on who the effect is applied;
     * @doing - creating a message for the Great Wizard.
     */
    public void createMessageForTheGreatWizard(final Hero hero) {
        /* Construct a message that says if the angel helped or not*/
        /* Stating the hero helped/hit */
        StringBuilder str = new StringBuilder();

        str.append(this.getTypeAngel());
        str.append(" ");
        str.append(this.getEffect());
        str.append(" ");
        str.append(hero.getHeroType());
        str.append(" ");
        str.append(hero.getPosInList());
        str.append("\n");

        /* Notifying the Great Wizard */
        this.setChanged();
        this.notifyObservers(str.toString());
    }

    /**
     * @param hero - the hero on who the effect is applied;
     * @doing - creating a message for the Great Wizard, if the hero is dead.
     */
    public void checkHeroCondition(final Hero hero) {
        /* Checking to see if the player died by an angel effect */
        if (hero.getCurrentHP() <= 0) {
            /* Creating a message to notify the Great Wizard */
            /* An angel killed a player */
            StringBuilder str = new StringBuilder("Player ");

            str.append(hero.getHeroType());
            str.append(" ");
            str.append(hero.getPosInList());
            str.append(" was killed by an angel\n");

            /* Notifying the Great Wizard */
            this.setChanged();
            this.notifyObservers(str.toString());
        }
    }

    /**
     * @param hero - the hero on who the effect is applied;
     * @doing - Checking to see if the hero got enough exp to level up and if true leveling him up.
     */
    public void checkHeroExp(final Hero hero) {
        /* Checking to see if the hero leveled up */
        if (hero.getExp() >= hero.getNecessaryExpToLevelUP()) {
            /* Level Up the hero */
            hero.levelUp();
        }
    }
}
