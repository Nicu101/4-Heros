package strategies;

import characters.Hero;

public class WizardStrategy implements AttackStrategy {
    /* Magic Numbers */
    private static final int DISPENSER_INFERIOR_LIMIT = 4;
    private static final int DISPENSER_SUPERIOR_LIMIT = 2;
    private static final int DISPENSER_DAMAGE_TAKEN = 10;
    private static final int DISPENSER_DAMAGE_HEALED = 5;

    /* Damage modifiers */
    private final float damageBonus = 0.6f;
    private final float damageReduction = -0.2f;

    /**
     * @param wizard - the hero on who the strategie is applied.
     * Applying this strategie to a hero; more specifically to a hero of type Wizard.
     */
    public void strategyToApply(final Hero wizard) {
        /* If the hero is incapacitated the strategie does not apply */
        if (wizard.getImmobility()) {
            return;
        }

        /* Strategie for the knight */
        double inferiorLimitHp;
        double superiorLimitHp;

        inferiorLimitHp = (double) wizard.getMaxHP() / DISPENSER_INFERIOR_LIMIT;
        superiorLimitHp = (double) wizard.getMaxHP() / DISPENSER_SUPERIOR_LIMIT;

        if ((inferiorLimitHp < wizard.getCurrentHP())
                && (wizard.getCurrentHP() < superiorLimitHp)) {
            /* More damage */
            /* Reduce current hp */
            wizard.takeDamage(wizard.getCurrentHP() / DISPENSER_DAMAGE_TAKEN);
            /* Increase damage */
            wizard.modifyCoefficients(this.damageBonus);
        } else if (wizard.getCurrentHP() < inferiorLimitHp) {
            /* Lee damage */
            /* Heal hp */
            wizard.setCurrentHP(wizard.getCurrentHP()
                                + wizard.getCurrentHP() / DISPENSER_DAMAGE_HEALED);
            /* Reduce damage */
            wizard.modifyCoefficients(this.damageReduction);
        }
    }
}
