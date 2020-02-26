package angels;

import characters.Knight;
import characters.Rogue;
import characters.Pyromancer;
import characters.Wizard;

public class DamageAngel extends Angel {
    private final String typeAngel = "DamageAngel";
    private final String effect = "helped";

    /* Modifiers for damage */
    private final float knightModifier = 0.15f;
    private final float pyroModifier = 0.2f;
    private final float rogueModifier = 0.3f;
    private final float wizardModifier = 0.4f;

    @Override
    public final String getTypeAngel() {
        return typeAngel;
    }

    @Override
    public final String getEffect() {
        return effect;
    }

    public DamageAngel(final int coordinateX, final int coordinateY) {
        super(coordinateX, coordinateY);
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

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(knight);
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

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(rogue);
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

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(pyro);
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

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(wizard);
    }

}
