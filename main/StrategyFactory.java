package main;

import strategies.AttackStrategy;
import strategies.KnightStrategy;
import strategies.RogueStrategy;
import strategies.PyromancerStrategy;
import strategies.WizardStrategy;

public class StrategyFactory {
    private final String typeKnight = "K";
    private final String typeRogue = "R";
    private final String typePyro = "P";

    /* Singleton instances */
    private KnightStrategy knightStrategy = null;
    private RogueStrategy rogueStrategy = null;
    private PyromancerStrategy pyroStrategy = null;
    private WizardStrategy wizardStrategy = null;

    /**
     * @param typeStrategy - the type of strategie that needs to be created.
     * @return A strategie to be used by one hero.
     */
    public AttackStrategy create(final String typeStrategy) {
        if (typeStrategy.equals(this.typeKnight)) {
            /* Creating the KnightStrategy instance if necessary */
            if (knightStrategy == null) {
                knightStrategy = new KnightStrategy();
            }
            return knightStrategy;
        } else if (typeStrategy.equals(this.typeRogue)) {
            /* Creating the RogueStrategy instance if necessary */
            if (rogueStrategy == null) {
                rogueStrategy = new RogueStrategy();
            }
            return rogueStrategy;
        } else if (typeStrategy.equals(this.typePyro)) {
            /* Creating the PyromancerStrategy instance if necessary */
            if (pyroStrategy == null) {
                pyroStrategy = new PyromancerStrategy();
            }
            return pyroStrategy;
        } else { // typeWizard
            /* Creating the WizardStrategy instance if necessary */
            if (wizardStrategy == null) {
                wizardStrategy = new WizardStrategy();
            }
            return wizardStrategy;
        }
    }
}
