package main;

import characters.Hero;
import characters.Knight;
import characters.Rogue;
import characters.Wizard;
import characters.Pyromancer;

public class HeroFactory {
    private final String typeKnight = "K";
    private final String typeRogue = "R";
    private final String typePyro = "P";

    /**
     * @param heroType - the type of hero that needs to be created.
     * @param coordinateX - Ox coordinate that the hero is found.
     * @param coordinateY - Oy coordinate that the hero is found.
     * @return A new hero with the coordinates received and of type given.
     */
    public Hero create(final String heroType, final int coordinateX,
                                  final int coordinateY) {
        // Create a new hero based on heroType
        if (heroType.equals(this.typeKnight)) {
            return new Knight(coordinateX, coordinateY);
        } else if (heroType.equals(this.typeRogue)) {
            return new Rogue(coordinateX, coordinateY);
        } else if (heroType.equals(this.typePyro)) {
            return new Pyromancer(coordinateX, coordinateY);
        } else {
            return new Wizard(coordinateX, coordinateY);
        }
    }
}
