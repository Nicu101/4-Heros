package angels;

import characters.Knight;
import characters.Rogue;
import characters.Pyromancer;
import characters.Wizard;

public class LevelUpAngel extends Angel {
    private final String typeAngel = "LevelUpAngel";
    private final String effect = "helped";

    /* Modifiers */
    private final float knightModifier = 0.1f;
    private final float pyroModifier = 0.2f;
    private final float rogueModifier = 0.15f;
    private final float wizardModifier = 0.25f;

    public LevelUpAngel(final int coordinateX, final int coordinateY) {
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

        /* Increase Knight damage */
        knight.modifyCoefficients(this.knightModifier);
        /* Gives exp for levelUp */
        knight.setExp(knight.getNecessaryExpToLevelUP());

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(knight);

        /* Checking to see if the hero has enough exp to level up */
        this.checkHeroExp(knight);
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

        /* Increase Rogue damage */
        rogue.modifyCoefficients(this.rogueModifier);
        /* Gives exp for levelUp */
        rogue.setExp(rogue.getNecessaryExpToLevelUP());

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(rogue);

        /* Checking to see if the hero has enough exp to level up */
        this.checkHeroExp(rogue);
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

        /* Increase Pyromancer damage */
        pyro.modifyCoefficients(this.pyroModifier);
        /* Gives exp for levelUp */
        pyro.setExp(pyro.getNecessaryExpToLevelUP());

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(pyro);

        /* Checking to see if the hero has enough exp to level up */
        this.checkHeroExp(pyro);
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

        /* Increase Wizard damage */
        wizard.modifyCoefficients(this.wizardModifier);
        /* Gives exp for levelUp */
        wizard.setExp(wizard.getNecessaryExpToLevelUP());

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(wizard);

        /* Checking to see if the hero has enough exp to level up */
        this.checkHeroExp(wizard);
    }
}
