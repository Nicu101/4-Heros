package characters;

import angels.Angel;
import map.Map;
import strategies.AttackStrategy;

import java.util.Observable;

public abstract class Hero extends Observable {
    private final int baseExpForLevel = 250;
    private final int bonusNecessaryExpPerLevel = 50;

    private AttackStrategy strategy;

    private int posInList;
    private int maxHP;
    private int currentHP;
    private int lvl;
    private int necessaryExpToLevelUP;
    private int exp;
    private int coordinateX;
    private int coordinateY;
    private int affectionDamage;
    private int affectionDuration;
    private boolean immobility;
    private boolean foughtThisRound;

    /* Magic number, so that Math.round() is always applied on float*/
    private final float magicNumber = 0.000f;

    protected Hero(final int coordinateX, final int coordinateY, final int startHP) {
        this.setCoordinateX(coordinateX);
        this.setCoordinateY(coordinateY);
        this.setExp(0);
        this.setCurrentHP(startHP);
        this.setLvl(0);
        this.setMaxHP(startHP);
        this.setNecessaryExpToLevelUP(this.getBaseExpForLevel());
        this.setAffectionDamage(0);
        this.setAffectionDuration(0);
        this.setImmobility(false);
    }

    /**
     * @return The strategy used by this hero.
     */
    public AttackStrategy getStrategy() {
        return strategy;
    }

    /**
     * Setting a strategy for this hero.
     */
    public void setStrategy(final AttackStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @return The position in the list for this hero.
     */
    public int getPosInList() {
        return posInList;
    }

    /**
     * Remembering the position in the list for this hero.
     */
    public void setPosInList(final int posInList) {
        this.posInList = posInList;
    }

    public final int getMaxHP() {
        return maxHP;
    }

    public final void setMaxHP(final int maxHP) {
        this.maxHP = maxHP;
    }

    public final int getCurrentHP() {
        return currentHP;
    }

    public final void setCurrentHP(final int currentHP) {
        this.currentHP = currentHP;
    }

    public final int getLvl() {
        return lvl;
    }

    public final void setLvl(final int lvl) {
        this.lvl = lvl;
    }

    public final int getNecessaryExpToLevelUP() {
        return necessaryExpToLevelUP;
    }

    public final void setNecessaryExpToLevelUP(final int necessaryExpToLevelUP) {
        this.necessaryExpToLevelUP = necessaryExpToLevelUP;
    }

    public final int getExp() {
        return exp;
    }

    public final void setExp(final int exp) {
        this.exp = exp;
    }

    public final int getCoordinateX() {
        return coordinateX;
    }

    public final void setCoordinateX(final int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public final int getCoordinateY() {
        return coordinateY;
    }

    public final void setCoordinateY(final int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public final int getAffectionDamage() {
        return affectionDamage;
    }

    public final void setAffectionDamage(final int affectionDamage) {
        this.affectionDamage = affectionDamage;
    }

    public final int getAffectionDuration() {
        return affectionDuration;
    }

    public final void setAffectionDuration(final int affectionDuration) {
        this.affectionDuration = affectionDuration;
    }

    public final int getBaseExpForLevel() {
        return baseExpForLevel;
    }

    public final boolean getImmobility() {
        return immobility;
    }

    public final void setImmobility(final boolean immobility) {
        this.immobility = immobility;
    }

    public final boolean getFoughtThisRound() {
        return foughtThisRound;
    }

    public final void setFoughtThisRound(final boolean foughtThisRound) {
        this.foughtThisRound = foughtThisRound;
    }

    public final double getMagicNumber() {
        return magicNumber;
    }

    public abstract void modifyCoefficients(float modifiedCoefficient);

    /**
     * Applying a strategie for this hero.
     */
    public void applyStrategy() {
        this.getStrategy().strategyToApply(this);
    }

    /**
     * @param enemyHero - enemy hero.
     * @param zone - type map.
     * @return - damage done by the spell with ground modifier.
     */
    public abstract int primarySpell(Hero enemyHero, Map zone);

    /**
     * @param enemyHero - enemy hero.
     * @param zone - type map.
     * @return - damage done by the spell with ground modifier.
     */
    public abstract int secondarySpell(Hero enemyHero, Map zone);

    public final void takeDamage(final int damage) {
        this.setCurrentHP(this.getCurrentHP() - damage);
    }

    public final void reduceAffectionDuration() {
        /* Applying the damage*/
        this.takeDamage(this.getAffectionDamage());

        /* 1 round passed = 1 less round for the duration of affection(DoT) */
        this.setAffectionDuration(this.getAffectionDuration() - 1);

        /* If the duration is 0, no effect is applied */
        if (this.getAffectionDuration() <= 0) {
            this.setImmobility(false);
        }
    }

    public abstract void levelUp();

    public final void levelUp(final int bonusLevelHp) {
        /* Remember the old level so that we can create a message to notify the great wizard */
        int oldLevel = this.getLvl();

        /* Calculate the number of levels should the hero advance */
        int calculateLevel;
        calculateLevel = this.getExp() - this.baseExpForLevel;
        calculateLevel = (int) Math.round(calculateLevel / this.bonusNecessaryExpPerLevel
                + this.getMagicNumber());
        calculateLevel++;

        /* Setting the new level, hp and necessary exp to level up */
        this.setLvl(calculateLevel);
        this.setMaxHP(this.getMaxHP() + bonusLevelHp * (calculateLevel - oldLevel));
        this.setNecessaryExpToLevelUP(this.baseExpForLevel
                + this.bonusNecessaryExpPerLevel * calculateLevel);

        /* Refilling hp to 100%*/
        this.setCurrentHP(this.getMaxHP());

        /* Creating a message to send to the Great Wizard */
        this.createMessageForTheGreatWizard(oldLevel);
    }

    /**
     * @param oldLevel - the hero level before method LevelUp(bonusLevelHp) was applied.
     * Notify the Great Wizard about the level up of a hero.
     */
    public void createMessageForTheGreatWizard(final int oldLevel) {
        /* Construct the level up message */
        StringBuilder str = new StringBuilder();

        for (int level = oldLevel + 1; level <= this.getLvl(); level++) {
            /* Append Hero type and his position in the list */
            str.append(this.getHeroType());
            str.append(" ");
            str.append(this.posInList);
            /* Append the level reached */
            str.append(" reached level ");
            str.append(level);
            /* New Line*/
            str.append("\n");
        }

        /* Notifying the Great Wizard */
        this.setChanged();
        this.notifyObservers(str.toString());
    }

    /**
     * Moves the hero in a specified direction.
     * @param direction - the direction for the move.
     */
    public void moveHero(final Character direction) {
        /* Dead heroes can't move. */
        if (this.getCurrentHP() <= 0) {
            return;
        }

        /* Check to see if the hero can move */
        if (this.getImmobility()) {
            /* The Hero can not move */
            if (this.getAffectionDuration() > 0) {
                this.reduceAffectionDuration();
            }

            return;
        }

        /* No move received */
        if (direction == null) {
            return;
        }

        /* Moving the hero on the map*/
        if (direction.equals('L')) {
            this.setCoordinateX(this.getCoordinateX() - 1);
        } else if (direction.equals('R')) {
            this.setCoordinateX(this.getCoordinateX() + 1);
        } else if (direction.equals('U')) {
            this.setCoordinateY(this.getCoordinateY() - 1);
        } else if (direction.equals('D')) {
            this.setCoordinateY(this.getCoordinateY() + 1);
        }

        /* Applying affection if necessary */
        if (this.getAffectionDuration() > 0) {
            this.reduceAffectionDuration();
        }
    }

    public abstract void dispatchAngelEffect(Angel angel);

    public abstract int calculateDamage(Hero enemyHero, Map zone);

    public abstract int acceptGroundModifier(Map zone, int damageToModify);

    protected abstract int acceptRaceModifierForPrimarySpell(Hero attackingHero,
                                                                int damagePrimarySpell);

    protected abstract int raceModifierForPrimarySpell(Rogue rogue,
                                                       int damagePrimarySpell);

    protected abstract int raceModifierForPrimarySpell(Knight knight,
                                                       int damagePrimarySpell);

    protected abstract int raceModifierForPrimarySpell(Pyromancer pyromancer,
                                                       int damagePrimarySpell);

    protected abstract int raceModifierForPrimarySpell(Wizard wizard,
                                                       int damagePrimarySpell);

    protected abstract int acceptRaceModifierForSecondarySpell(Hero attackingHero,
                                                               int damageSecondarySpell);

    protected abstract int raceModifierForSecondarySpell(Rogue rogue,
                                                         int damagePrimarySpell);

    protected abstract int raceModifierForSecondarySpell(Knight knight,
                                                         int damagePrimarySpell);

    protected abstract int raceModifierForSecondarySpell(Pyromancer pyromancer,
                                                         int damagePrimarySpell);

    protected abstract int raceModifierForSecondarySpell(Wizard wizard,
                                                         int damagePrimarySpell);

    public abstract String getTypeHero();

    public abstract String getHeroType();

    public final String toString() {
        /* Creating a message with information about the hero*/
        StringBuilder str = new StringBuilder();

        /* Appending the type a hero*/
        str.append(this.getTypeHero());

        /* Checking to see if the hero is alive*/
        if (this.getCurrentHP() <= 0) {
            /* The hero is dead, returning a different message*/
            str.append(" dead");
            return str.toString();
        }

        /* Adding information about this hero level */
        str.append(" ");
        str.append(this.getLvl());
        /* Adding information about this hero  current exp*/
        str.append(" ");
        str.append(this.getExp());
        /* Adding information about this hero current hp*/
        str.append(" ");
        str.append(this.getCurrentHP());
        /* Adding information about this hero row position*/
        str.append(" ");
        str.append(this.getCoordinateY());
        /* Adding information about this hero col position*/
        str.append(" ");
        str.append(this.getCoordinateX());

        /* Returning a basic description about the hero*/
        return str.toString();
    }
}
