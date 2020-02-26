package strategies;

import characters.Hero;

public interface AttackStrategy {
    /* This exist only so that the strategies are encapsulated in one place */

    void strategyToApply(Hero hero);
}
