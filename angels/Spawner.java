package angels;

import characters.Knight;
import characters.Rogue;
import characters.Pyromancer;
import characters.Wizard;
import characters.Hero;

public class Spawner extends Angel {
    private final String typeAngel = "Spawner";
    private final String effect = "helped";

    /* Modifiers */
    private final int knightHpRespawn = 200;
    private final int pyroHpRespawn = 150;
    private final int rogueHpRespawn = 180;
    private final int wizardHpRespawn = 120;

    public Spawner(final int coordinateX, final int coordinateY) {
        super(coordinateX, coordinateY);
    }

    @Override
    public final String getTypeAngel() {
        return typeAngel;
    }

    @Override
    public final String getEffect() {
        return effect;
    }

    /**
     * @param knight - the hero on who the effect is applied; can only be of type Knight.
     * @doing - applying the angel effect on the hero.
     */
    @Override
    public void angelEffect(final Knight knight) {
        /* Respawn the Knight and set his new hp if the hero is dead*/
        if (knight.getCurrentHP() <= 0) {
            knight.setCurrentHP(this.knightHpRespawn);

            /* Create the message for the Great Wizard */
            this.createMessageForTheGreatWizard(knight);

            /* Notifying the Great Wizard about a respawn */
            this.respawnHero(knight);
        }
    }

    /**
     * @param rogue - the hero on who the effect is applied; can only be of type Rogue.
     * @doing - applying the angel effect on the hero.
     */
    @Override
    public void angelEffect(final Rogue rogue) {
        /* Respawn the Rogue and set his new hp if the hero is dead*/
        if (rogue.getCurrentHP() <= 0) {
            rogue.setCurrentHP(this.rogueHpRespawn);

            /* Create the message for the Great Wizard */
            this.createMessageForTheGreatWizard(rogue);

            /* Notifying the Great Wizard about a respawn */
            this.respawnHero(rogue);
        }
    }

    /**
     * @param pyro - the hero on who the effect is applied; can only be of type Pyromancer.
     * @doing - applying the angel effect on the hero.
     */
    @Override
    public void angelEffect(final Pyromancer pyro) {
        /* Respawn the Pyromancer and set his new hp if the hero is dead*/
        if (pyro.getCurrentHP() <= 0) {
            pyro.setCurrentHP(this.pyroHpRespawn);

            /* Create the message for the Great Wizard */
            this.createMessageForTheGreatWizard(pyro);

            /* Notifying the Great Wizard about a respawn */
            this.respawnHero(pyro);
        }
    }

    /**
     * @param wizard - the hero on who the effect is applied; can only be of type Wizard.
     * @doing - applying the angel effect on the hero.
     */
    @Override
    public void angelEffect(final Wizard wizard) {
        /* Respawn the Wizard and set his new hp if the hero is dead*/
        if (wizard.getCurrentHP() <= 0) {
            wizard.setCurrentHP(this.wizardHpRespawn);

            /* Create the message for the Great Wizard */
            this.createMessageForTheGreatWizard(wizard);

            /* Notifying the Great Wizard about a respawn */
            this.respawnHero(wizard);
        }
    }

    /**
     * @param hero - the hero on who the effect is applied.
     * @doing - respawning the hero and notifying the Great Wizard about this.
     */
    public void respawnHero(final Hero hero) {
        /* Creating a message for the Great Wizard that a hero was brought to life */
        StringBuilder str = new StringBuilder("Player ");

        str.append(hero.getHeroType());
        str.append(" ");
        str.append(hero.getPosInList());
        str.append(" was brought to life by an angel\n");

        /* Notifying the Great Wizard */
        this.setChanged();
        this.notifyObservers(str.toString());
    }
}
