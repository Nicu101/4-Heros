package map;

import characters.Knight;
import characters.Pyromancer;
import characters.Rogue;
import characters.Wizard;

public abstract class Map {

    /* Magic number, so that Math.round() is always applied on float*/
    private final float magicNumber = 0.00f;

    /* Paralysis normal duration */
    private final int paralysisNormalDuration = 3;

    public final double getMagicNumber() {
        return magicNumber;
    }

    public static Map addMapType(final Character typeMap) {
        /* Return a map of the type specified*/
        if (typeMap.equals('W')) {
            return Woods.getWoodsInstance();
        } else if (typeMap.equals('D')) {
            return Desert.getDesertInstance();
        } else if (typeMap.equals('V')) {
            return Volcanic.getVolcanicInstance();
        } else {
            return Land.getLandInstance();
        }
    }

    /* These methods are Overridden as needed */

    /* This method may be overridden by class Woods if the first parameter is a Rogue*/
    public int groundModifier(final Rogue rogue, final int damageToModify) {
        return damageToModify;
    }

    /* This method may be overridden by class Land if the first parameter is a Knight*/
    public int groundModifier(final Knight knight, final int damageToModify) {
        return damageToModify;
    }

    /* This method may be overridden by class Volcanic if the first parameter is a Pyromancer*/
    public int groundModifier(final Pyromancer pyromancer, final int damageToModify) {
        return damageToModify;
    }

    /* This method may be overridden by class Desert if the first parameter is a Wizard*/
    public int groundModifier(final Wizard wizard, final int damageToModify) {
        return damageToModify;
    }

    /* This method is overridden by class Woods so that in can apply backstab critical bonus */
    public int backstabCriticalStrike(final int damage) {
        return damage;
    }

    /* Returning paralysis duration outside of woods ground */
    public int paralysisDuration() {
        return this.paralysisNormalDuration;
    }
}
