package map;

import characters.Pyromancer;

public class Volcanic extends Map {
    private static Volcanic volcanicInstance = null;

    /* Land modifier */
    private final float pyroModifier = 1.25f;

    public static Volcanic getVolcanicInstance() {
        /* Creating a singleton */
        if (volcanicInstance == null) {
            volcanicInstance = new Volcanic();
        }
        return volcanicInstance;
    }

    @Override
    public final int groundModifier(final Pyromancer pyromancer, final int damageToModify) {
        /* Modifying the damage if we have a pyromancer in a Volcanic region */
        return (int) Math.round((float) damageToModify * this.pyroModifier
                + this.getMagicNumber());
    }
}
