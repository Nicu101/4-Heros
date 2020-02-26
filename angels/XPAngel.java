package angels;

import characters.Knight;
import characters.Rogue;
import characters.Pyromancer;
import characters.Wizard;

public class XPAngel extends Angel {
    private final String typeAngel = "XPAngel";
    private final String effect = "helped";

    /* Modifiers */
    private final int knightXpModifier = 45;
    private final int pyroXpModifier = 50;
    private final int rogueXpModifier = 40;
    private final int wizardXpModifier = 60;

    public XPAngel(final int coordinateX, final int coordinateY) {
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

        /* Gives exp for levelUp */
        knight.setExp(knight.getExp() + this.knightXpModifier);

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

        /* Gives exp for levelUp */
        rogue.setExp(rogue.getExp() + this.rogueXpModifier);

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

        /* Gives exp for levelUp */
        pyro.setExp(pyro.getExp() + this.pyroXpModifier);

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

        /* Gives exp for levelUp */
        wizard.setExp(wizard.getExp() + this.wizardXpModifier);

        /* Create the message for the Great Wizard */
        this.createMessageForTheGreatWizard(wizard);

        /* Checking to see if the hero has enough exp to level up */
        this.checkHeroExp(wizard);
    }

}
