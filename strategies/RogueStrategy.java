package strategies;

import characters.Hero;

public class RogueStrategy implements AttackStrategy {
    /* Magic Numbers */
    private static final int DISPENSER_INFERIOR_LIMIT = 7;
    private static final int DISPENSER_SUPERIOR_LIMIT = 5;
    private static final int DISPENSER_DAMAGE_TAKEN = 7;
    private static final int DISPENSER_DAMAGE_HEALED = 2;

    /* Damage modifiers */
    private final float damageBonus = 0.4f;
    private final float damageReduction = -0.1f;

    /**
     * @param rogue - the hero on who the strategie is applied.
     * Applying this strategie to a hero; more specifically to a hero of type Rogue.
     */
    public void strategyToApply(final Hero rogue) {
        /* If the hero is incapacitated the strategie does not apply */
        if (rogue.getImmobility()) {
            return;
        }

        /* Strategie for the knight */
        double inferiorLimitHp;
        double superiorLimitHp;

        inferiorLimitHp = (double) rogue.getMaxHP() / DISPENSER_INFERIOR_LIMIT;
        superiorLimitHp = (double) rogue.getMaxHP() / DISPENSER_SUPERIOR_LIMIT;

        if ((inferiorLimitHp < rogue.getCurrentHP())
                && (rogue.getCurrentHP() < superiorLimitHp)) {
            /* More damage */
            /* Reduce current hp */
            rogue.takeDamage(rogue.getCurrentHP() / DISPENSER_DAMAGE_TAKEN);
            /* Increase damage */
            rogue.modifyCoefficients(this.damageBonus);
        } else if (rogue.getCurrentHP() < inferiorLimitHp) {
            /* Lee damage */
            /* Heal hp */
            rogue.setCurrentHP(rogue.getCurrentHP()
                                + rogue.getCurrentHP() / DISPENSER_DAMAGE_HEALED);
            /* Reduce damage */
            rogue.modifyCoefficients(this.damageReduction);
        }
    }
}
