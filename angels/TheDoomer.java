package angels;

import characters.Knight;
import characters.Rogue;
import characters.Pyromancer;
import characters.Wizard;

public class TheDoomer extends Angel {
    private final String typeAngel = "TheDoomer";
    private final String effect = "hit";

    /* Modifiers */
    private final int heroHpWhenDead = -1;

    public TheDoomer(final int coordinateX, final int coordinateY) {
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
        /* No effects are applied if the hero is dead */
        if (knight.getCurrentHP() <= 0) {
            return;
        }

        /* Kills the Knight */
        knight.setCurrentHP(this.heroHpWhenDead);

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(knight);

        /* Checking to see if the ange killed the hero */
        /* And if yes, the Great Wizard will be notify */
        this.checkHeroCondition(knight);
    }

    /**
     * @param rogue - the hero on who the effect is applied; can only be of type Rogue.
     * @doing - applying the angel effect on the hero.
     */
    @Override
    public void angelEffect(final Rogue rogue) {
        /* No effects are applied if the hero is dead */
        if (rogue.getCurrentHP() <= 0) {
            return;
        }

        /* Kills the Rogue */
        rogue.setCurrentHP(this.heroHpWhenDead);

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(rogue);

        /* Checking to see if the ange killed the hero */
        /* And if yes, the Great Wizard will be notify */
        this.checkHeroCondition(rogue);
    }

    /**
     * @param pyro - the hero on who the effect is applied; can only be of type Pyromancer.
     * @doing - applying the angel effect on the hero.
     */
    @Override
    public void angelEffect(final Pyromancer pyro) {
        /* No effects are applied if the hero is dead */
        if (pyro.getCurrentHP() <= 0) {
            return;
        }

        /* Kills the Pyromancer */
        pyro.setCurrentHP(this.heroHpWhenDead);

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(pyro);

        /* Checking to see if the ange killed the hero */
        /* And if yes, the Great Wizard will be notify */
        this.checkHeroCondition(pyro);
    }

    /**
     * @param wizard - the hero on who the effect is applied; can only be of type Wizard.
     * @doing - applying the angel effect on the hero.
     */
    @Override
    public void angelEffect(final Wizard wizard) {
        /* No effects are applied if the hero is dead */
        if (wizard.getCurrentHP() <= 0) {
            return;
        }

        /* Kills the Wizard */
        wizard.setCurrentHP(this.heroHpWhenDead);

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(wizard);

        /* Checking to see if the ange killed the hero */
        /* And if yes, the Great Wizard will be notify */
        this.checkHeroCondition(wizard);
    }
}
