package strategies;

import characters.Hero;

public class KnightStrategy implements AttackStrategy {
    /* Magic Numbers */
    private static final int DISPENSER_INFERIOR_LIMIT = 3;
    private static final int DISPENSER_SUPERIOR_LIMIT = 2;
    private static final int DISPENSER_DAMAGE_TAKEN = 2;
    private static final int DISPENSER_DAMAGE_HEALED = 4;

    /* Damage modifiers */
    private final float damageBonus = 0.5f;
    private final float damageReduction = -0.2f;

    /**
     * @param knight - the hero on who the strategie is applied.
     * Applying this strategie to a hero; more specifically to a hero of type Knight.
     */
    public void strategyToApply(final Hero knight) {
        /* If the hero is incapacitated the strategie does not apply */
        if (knight.getImmobility()) {
            return;
        }

        /* Strategie for the knight */
        double inferiorLimitHp;
        double superiorLimitHp;

        inferiorLimitHp = (double) knight.getMaxHP() / DISPENSER_INFERIOR_LIMIT;
        superiorLimitHp = (double) knight.getMaxHP() / DISPENSER_SUPERIOR_LIMIT;

        if ((inferiorLimitHp < knight.getCurrentHP())
                && (knight.getCurrentHP() < superiorLimitHp)) {
            /* More damage */
            /* Reduce current hp */
            knight.takeDamage(knight.getCurrentHP() / DISPENSER_DAMAGE_TAKEN);
            /* Increase damage */
            knight.modifyCoefficients(this.damageBonus);
        } else if (knight.getCurrentHP() < inferiorLimitHp) {
            /* Lee damage */
            /* Heal hp */
            knight.setCurrentHP(knight.getCurrentHP()
                                + knight.getCurrentHP() / DISPENSER_DAMAGE_HEALED);
            /* Reduce damage */
            knight.modifyCoefficients(this.damageReduction);
        }
    }
}
