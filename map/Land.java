package map;

import characters.Knight;

public class Land extends Map {
    private static Land landInstance = null;

    /* Land modifier */
    private final float knightModifier = 1.15f;


    public static Land getLandInstance() {
        /* Creating a singleton */
        if (landInstance == null) {
            landInstance = new Land();
        }
        return landInstance;
    }

    @Override
    public final int groundModifier(final Knight knight, final int damageToModify) {
        /* Modifying the damage if we have a knight on the Land */
        return (int) Math.round((float) damageToModify * this.knightModifier
                + this.getMagicNumber());
    }
}
