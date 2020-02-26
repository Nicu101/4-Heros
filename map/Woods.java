package map;

import characters.Rogue;

public class Woods extends Map {
    private static Woods woodsInstance = null;

    /* Land modifier */
    private final float rogueModifier = 1.15f;

    /* Backstab critical modifier */
    private final double criticalBonus = 1.5;
    /* Paralysis enhanced duration */
    private final int paralysisEnhancedDuration = 6;

    public static Woods getWoodsInstance() {
        /* Creating a singleton */
        if (woodsInstance == null) {
            woodsInstance = new Woods();
        }
        return woodsInstance;
    }

    @Override
    public final int groundModifier(final Rogue rogue, final int damageToModify) {
        /* Modifying the damage if we have a rogue in the Woods */
        return (int) Math.round((float) damageToModify * this.rogueModifier
                + this.getMagicNumber());
    }

    @Override
    public final int backstabCriticalStrike(final int damage) {
        return (int) Math.round((double) damage * this.criticalBonus
                + this.getMagicNumber());
    }

    /**
     * @return The enhanced duration for Rogue Paralysis spell.
     */
    @Override
    public int paralysisDuration() {
        return this.paralysisEnhancedDuration;
    }
}
