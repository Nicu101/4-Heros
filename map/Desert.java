package map;

import characters.Wizard;

public class Desert extends Map {
    private static Desert desertInstance = null;

    /* Land modifier */
    private final float wizardModifier = 1.1f;

    public static Desert getDesertInstance() {
        /* Creating a singleton */
        if (desertInstance == null) {
            desertInstance = new Desert();
        }
        return desertInstance;
    }

    @Override
    public final int groundModifier(final Wizard wizard, final int damageToModify) {
        /* Modifying the damage if we have a wizard in the Desert */
        return (int) Math.round((float) damageToModify * this.wizardModifier
                + this.getMagicNumber());
    }
}
