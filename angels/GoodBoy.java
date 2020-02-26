package angels;

import characters.Knight;
import characters.Rogue;
import characters.Pyromancer;
import characters.Wizard;

public class GoodBoy extends Angel {
    private final String typeAngel = "GoodBoy";
    private final String effect = "helped";

    /* Modifiers */
    private final float knightModifier = 0.4f;
    private final int knightHpModifier = 20;
    private final float pyroModifier = 0.5f;
    private final int pyroHpModifier = 30;
    private final float rogueModifier = 0.4f;
    private final int rogueHpModifier = 40;
    private final float wizardModifier = 0.3f;
    private final int wizardHpModifier = 50;

    public GoodBoy(final int coordinateX, final int coordinateY) {
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
        /* Increase Knight hp */
        int hp = knight.getCurrentHP() + this.knightHpModifier;
        /* Checking so that the hp is at maximum equal with max possible hp for the hero */
        if (hp > knight.getMaxHP()) {
            hp = knight.getMaxHP();
        }
        /* Setting the new HP */
        knight.setCurrentHP(hp);

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
        /* Increase Rogue hp */
        int hp = rogue.getCurrentHP() + this.rogueHpModifier;
        /* Checking so that the hp is at maximum equal with max possible hp for the hero */
        if (hp > rogue.getMaxHP()) {
            hp = rogue.getMaxHP();
        }
        /* Setting the new HP */
        rogue.setCurrentHP(hp);

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
        /* Increase Pyromancer hp */
        int hp = pyro.getCurrentHP() + this.pyroHpModifier;
        /* Checking so that the hp is at maximum equal with max possible hp for the hero */
        if (hp > pyro.getMaxHP()) {
            hp = pyro.getMaxHP();
        }
        /* Setting the new HP */
        pyro.setCurrentHP(hp);

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
        /* Increase Wizard hp */
        int hp = wizard.getCurrentHP() + this.wizardHpModifier;
        /* Checking so that the hp is at maximum equal with max possible hp for the hero */
        if (hp > wizard.getMaxHP()) {
            hp = wizard.getMaxHP();
        }
        /* Setting the new HP */
        wizard.setCurrentHP(hp);

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(wizard);
    }
}
