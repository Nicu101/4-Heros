package angels;

import characters.Knight;
import characters.Rogue;
import characters.Pyromancer;
import characters.Wizard;

public class Dracula extends Angel {
    private final String typeAngel = "Dracula";
    private final String effect = "hit";

    /* Modifiers */
    private final float knightModifier = -0.2f;
    private final int knightHpModifier = 60;
    private final float pyroModifier = -0.3f;
    private final int pyroHpModifier = 40;
    private final float rogueModifier = -0.1f;
    private final int rogueHpModifier = 35;
    private final float wizardModifier = -0.4f;
    private final int wizardHpModifier = 20;

    public Dracula(final int coordinateX, final int coordinateY) {
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

        /* Lower Knight damage */
        knight.modifyCoefficients(this.knightModifier);
        /* Lower Knight hp */
        knight.takeDamage(this.knightHpModifier);

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

        /* Lower Rogue damage */
        rogue.modifyCoefficients(this.rogueModifier);
        /* Lower Rogue hp */
        rogue.takeDamage(this.rogueHpModifier);

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

        /* Lower Pyromancer damage */
        pyro.modifyCoefficients(this.pyroModifier);
        /* Lower Pyromancer hp */
        pyro.takeDamage(this.pyroHpModifier);

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

        /* Lower Wizard damage */
        wizard.modifyCoefficients(this.wizardModifier);
        /* Lower Wizard hp */
        wizard.takeDamage(this.wizardHpModifier);

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(wizard);

        /* Checking to see if the ange killed the hero */
        /* And if yes, the Great Wizard will be notify */
        this.checkHeroCondition(wizard);
    }
}
