package strategies;

import characters.Hero;

public class PyromancerStrategy implements AttackStrategy {
    /* Magic Numbers */
    private static final int DISPENSER_INFERIOR_LIMIT = 4;
    private static final int DISPENSER_SUPERIOR_LIMIT = 3;
    private static final int DISPENSER_DAMAGE_TAKEN = 4;
    private static final int DISPENSER_DAMAGE_HEALED = 3;

    /* Damage modifiers */
    private final float damageBonus = 0.7f;
    private final float damageReduction = -0.3f;

    /**
     * @param pyromancer - the hero on who the strategie is applied.
     * Applying this strategie to a hero; more specifically to a hero of type Pyromancer.
     */
    public void strategyToApply(final Hero pyromancer) {
        /* If the hero is incapacitated the strategie does not apply */
        if (pyromancer.getImmobility()) {
            return;
        }

        /* Strategie for the pyromancer */
        double inferiorLimitHp;
        double superiorLimitHp;

        inferiorLimitHp = (double) pyromancer.getMaxHP() / DISPENSER_INFERIOR_LIMIT;
        superiorLimitHp = (double) pyromancer.getMaxHP() / DISPENSER_SUPERIOR_LIMIT;

        if ((inferiorLimitHp < pyromancer.getCurrentHP())
                && (pyromancer.getCurrentHP() < superiorLimitHp)) {
            /* More damage */
            /* Reduce current hp */
            pyromancer.takeDamage(pyromancer.getCurrentHP() / DISPENSER_DAMAGE_TAKEN);
            /* Increase damage */
            pyromancer.modifyCoefficients(this.damageBonus);
        } else if (pyromancer.getCurrentHP() < inferiorLimitHp) {
            /* Lee damage */
            /* Heal hp */
            pyromancer.setCurrentHP(pyromancer.getCurrentHP()
                                    + pyromancer.getCurrentHP() / DISPENSER_DAMAGE_HEALED);
            /* Reduce damage */
            pyromancer.modifyCoefficients(this.damageReduction);
        }
    }
}
