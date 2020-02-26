package characters;

import angels.Angel;
import map.Map;

public class Rogue extends Hero {
    private final String typeHero = "R";
    private final String heroType = "Rogue";
    private static final int START_HP = 600;
    private final int bonusLevelHp = 40;
    private final int startPrimarySpellDamage = 200;
    private final int bonusLevelPrimarySpellDamage = 20;
    private final int criticalBackstabTrigger = 3;
    private final int startSecondarySpellDamage = 40;
    private final int bonusLevelSecondarySpellDamage = 10;

    /* Magic Numbers */
    private static final float BACKSTAB_ROGUE_TO_ROGUE_START_MODIFIER = 1.2f;
    private static final float BACKSTAB_ROGUE_TO_KNIGHT_START_MODIFIER = 0.9f;
    private static final float BACKSTAB_ROGUE_TO_PYRO_START_MODIFIER = 1.25f;
    private static final float BACKSTAB_ROGUE_TO_WIZARD_START_MODIFIER = 1.25f;
    private static final float PARALYSIS_ROGUE_TO_ROGUE_START_MODIFIER = 0.9f;
    private static final float PARALYSIS_ROGUE_TO_KNIGHT_START_MODIFIER = 0.8f;
    private static final float PARALYSIS_ROGUE_TO_PYRO_START_MODIFIER = 1.2f;
    private static final float PARALYSIS_ROGUE_TO_WIZARD_START_MODIFIER = 1.25f;

    /* Modifiers for the spells */
    private float backstabRogueToRogueModifier;
    private float backstabRogueToKnightModifier;
    private float backstabRogueToPyroModifier;
    private float backstabRogueToWizardModifier;
    private float paralysisRogueToRogueModifier;
    private float paralysisRogueToKnightModifier;
    private float paralysisRogueToPyroModifier;
    private float paralysisRogueToWizardModifier;

    private int nrAttacks;

    public Rogue(final int coordinateX, final int coordinateY) {
        super(coordinateX, coordinateY, START_HP);
        this.setFoughtThisRound(false);
        this.setNrAttacks(2);
        this.backstabRogueToRogueModifier = BACKSTAB_ROGUE_TO_ROGUE_START_MODIFIER;
        this.backstabRogueToKnightModifier = BACKSTAB_ROGUE_TO_KNIGHT_START_MODIFIER;
        this.backstabRogueToPyroModifier = BACKSTAB_ROGUE_TO_PYRO_START_MODIFIER;
        this.backstabRogueToWizardModifier = BACKSTAB_ROGUE_TO_WIZARD_START_MODIFIER;
        this.paralysisRogueToRogueModifier = PARALYSIS_ROGUE_TO_ROGUE_START_MODIFIER;
        this.paralysisRogueToKnightModifier = PARALYSIS_ROGUE_TO_KNIGHT_START_MODIFIER;
        this.paralysisRogueToPyroModifier = PARALYSIS_ROGUE_TO_PYRO_START_MODIFIER;
        this.paralysisRogueToWizardModifier = PARALYSIS_ROGUE_TO_WIZARD_START_MODIFIER;
    }

    public final String getHeroType() {
        return heroType;
    }

    public final float getBackstabRogueToRogueModifier() {
        return backstabRogueToRogueModifier;
    }

    public final void setBackstabRogueToRogueModifier(final float backstabRogueToRogueModifier) {
        this.backstabRogueToRogueModifier = backstabRogueToRogueModifier;
    }

    public final float getBackstabRogueToKnightModifier() {
        return backstabRogueToKnightModifier;
    }

    public final void setBackstabRogueToKnightModifier(final float backstabRogueToKnightModifier) {
        this.backstabRogueToKnightModifier = backstabRogueToKnightModifier;
    }

    public final float getBackstabRogueToPyroModifier() {
        return backstabRogueToPyroModifier;
    }

    public final void setBackstabRogueToPyroModifier(final float backstabRogueToPyroModifier) {
        this.backstabRogueToPyroModifier = backstabRogueToPyroModifier;
    }

    public final float getBackstabRogueToWizardModifier() {
        return backstabRogueToWizardModifier;
    }

    public final void setBackstabRogueToWizardModifier(final float backstabRogueToWizardModifier) {
        this.backstabRogueToWizardModifier = backstabRogueToWizardModifier;
    }

    public final float getParalysisRogueToRogueModifier() {
        return paralysisRogueToRogueModifier;
    }

    public final void setParalysisRogueToRogueModifier(final float paralysisRogueToRogueModifier) {
        this.paralysisRogueToRogueModifier = paralysisRogueToRogueModifier;
    }

    public final float getParalysisRogueToKnightModifier() {
        return paralysisRogueToKnightModifier;
    }

    public final void setParalysisRogueToKnightModifier(
            final float paralysisRogueToKnightModifier) {
        this.paralysisRogueToKnightModifier = paralysisRogueToKnightModifier;
    }

    public final float getParalysisRogueToPyroModifier() {
        return paralysisRogueToPyroModifier;
    }

    public final void setParalysisRogueToPyroModifier(final float paralysisRogueToPyroModifier) {
        this.paralysisRogueToPyroModifier = paralysisRogueToPyroModifier;
    }

    public final float getParalysisRogueToWizardModifier() {
        return paralysisRogueToWizardModifier;
    }

    public final void setParalysisRogueToWizardModifier(
            final float paralysisRogueToWizardModifier) {
        this.paralysisRogueToWizardModifier = paralysisRogueToWizardModifier;
    }

    public final void modifyCoefficients(final float modifiedCoefficient) {
        /* Modify race coefficients for spell BACKSTAB */
        this.setBackstabRogueToKnightModifier(this.getBackstabRogueToKnightModifier()
                + modifiedCoefficient);
        this.setBackstabRogueToPyroModifier(this.getBackstabRogueToPyroModifier()
                + modifiedCoefficient);
        this.setBackstabRogueToRogueModifier(this.getBackstabRogueToRogueModifier()
                + modifiedCoefficient);
        this.setBackstabRogueToWizardModifier(this.getBackstabRogueToWizardModifier()
                + modifiedCoefficient);
        /* Modify race coefficients for spell PARALYSIS */
        this.setParalysisRogueToKnightModifier(this.getParalysisRogueToKnightModifier()
                + modifiedCoefficient);
        this.setParalysisRogueToPyroModifier(this.getParalysisRogueToPyroModifier()
                + modifiedCoefficient);
        this.setParalysisRogueToRogueModifier(this.getParalysisRogueToRogueModifier()
                + modifiedCoefficient);
        this.setParalysisRogueToWizardModifier(this.getParalysisRogueToWizardModifier()
                + modifiedCoefficient);
    }

    public final String getTypeHero() {
        return typeHero;
    }

    public final int getNrAttacks() {
        return nrAttacks;
    }

    public final void setNrAttacks(final int nrAttacks) {
        this.nrAttacks = nrAttacks;
    }

    public final void levelUp() {
        /* Level UP*/
        /* Used so that we can keep the bonus hp in the class*/
        super.levelUp(this.bonusLevelHp);
    }

    @Override
    public final int primarySpell(final Hero enemyHero, final Map zone) {
        /* Using BACKSTAB */
        int damage;

        /* Calculating the damage to be done by the spell*/
        damage = this.startPrimarySpellDamage + this.bonusLevelPrimarySpellDamage * this.getLvl();

        /* The 3rd use of Backstab is a critical hit */
        if (this.getNrAttacks() == 2) {
            damage = zone.backstabCriticalStrike(damage);
        }

        /* Applying ground modifier*/
        damage = this.acceptGroundModifier(zone, damage);

        return damage;
    }

    @Override
    public final int secondarySpell(final Hero enemyHero, final Map zone) {
        /* Using PARALYSIS*/
        int damage;

        /* Calculating the damage to be done by the spell*/
        damage = this.startSecondarySpellDamage
                + this.bonusLevelSecondarySpellDamage * this.getLvl();

        /* Setting the DOT(damage over time), duration and the immobility*/
        enemyHero.setAffectionDamage(damage);
        enemyHero.setAffectionDuration(zone.paralysisDuration());
        enemyHero.setImmobility(true);

        /* Applying ground modifier*/
        damage = this.acceptGroundModifier(zone, damage);

        return damage;
    }

    @Override
    public final void moveHero(final Character direction) {
        /* This method is overrode for this class   `
         * so that counting of backstab is possible*/
        if (this.getFoughtThisRound()) {
            this.setNrAttacks(this.getNrAttacks() + 1);

            /* Reset the attack counter if backstab was used 3 times */
            if (this.getNrAttacks() == this.criticalBackstabTrigger) {
                this.setNrAttacks(0);
            }
        }

        /* Applying the first move function */
        super.moveHero(direction);
    }

    @Override
    public final void dispatchAngelEffect(final Angel angel) {
        /* Angel strategie is applied */
        angel.angelEffect(this);
    }

    @Override
    public final int acceptGroundModifier(final Map zone, final int damageToModify) {
        return zone.groundModifier(this, damageToModify);
    }

    public final int acceptRaceModifierForPrimarySpell(final Hero attackingHero,
                                                       final int damagePrimarySpell) {
        /* Using double dispatch so that this is transmitting enemy type */
        /* this = enemy hero type */
        /* attackingHero = the hero that is launching spells (attacking) */
        return attackingHero.raceModifierForPrimarySpell(this, damagePrimarySpell);
    }

    public final int raceModifierForPrimarySpell(final Rogue rogue,
                                                 final int damageToModify) {
        /* Calculating damage from Rogue to Rogue for Backstab*/
        return (int) Math.round((float) damageToModify * this.backstabRogueToRogueModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Knight knight,
                                                 final int damageToModify) {
        /* Calculating damage from Rogue to Knight for Backstab*/
        return (int) Math.round((float) damageToModify * this.backstabRogueToKnightModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Pyromancer pyromancer,
                                                 final int damageToModify) {
        /* Calculating damage from Rogue to Pyromancer for Backstab*/
        return (int) Math.round((float) damageToModify * this.backstabRogueToPyroModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Wizard wizard,
                                                 final int damageToModify) {
        /* Calculating damage from Rogue to Wizard for Backstab*/
        return (int) Math.round((float) damageToModify * this.backstabRogueToWizardModifier
                + this.getMagicNumber());
    }

    public final int acceptRaceModifierForSecondarySpell(final Hero attackingHero,
                                                         final int damagePrimarySpell) {
        /* Using double dispatch so that this is transmitting enemy type */
        /* this = enemy hero type */
        /* attackingHero = the hero that is launching spells (attacking) */
        return attackingHero.raceModifierForSecondarySpell(this, damagePrimarySpell);
    }

    public final int raceModifierForSecondarySpell(final Rogue rogue,
                                                   final int damageToModify) {
        /* Calculating damage from Rogue to Rogue for Paralysis*/
        return (int) Math.round((float) damageToModify * this.paralysisRogueToRogueModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Knight knight,
                                                   final int damageToModify) {
        /* Calculating damage from Rogue to Knight for Paralysis*/
        return (int) Math.round((float) damageToModify * this.paralysisRogueToKnightModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Pyromancer pyromancer,
                                                   final int damageToModify) {
        /* Calculating damage from Rogue to Pyromancer for Paralysis*/
        return (int) Math.round((float) damageToModify * this.paralysisRogueToPyroModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Wizard wizard,
                                                   final int damageToModify) {
        /* Calculating damage from Rogue to Wizard for Paralysis*/
        return (int) Math.round((float) damageToModify * this.paralysisRogueToWizardModifier
                + this.getMagicNumber());
    }

    public final int calculateDamage(final Hero enemyHero, final Map zone) {
        /* Using both abilities with all the modifiers */
        int damagePrimarySpell;
        int damageSecSpell;

        /* Calculating the damage for Backstab */
        damagePrimarySpell = this.primarySpell(enemyHero, zone);
        /* Applying race modifiers for Backstab*/
        damagePrimarySpell = enemyHero.acceptRaceModifierForPrimarySpell(this,
                damagePrimarySpell);

        /* Calculating the damage for Paralysis */
        damageSecSpell = this.secondarySpell(enemyHero, zone);
        /* Applying race modifiers for Paralysis*/
        damageSecSpell = enemyHero.acceptRaceModifierForSecondarySpell(this, damageSecSpell);

        /* Set the new damage over time*/
        enemyHero.setAffectionDamage(damageSecSpell);

        /* Returning the damage for both spells*/
        return damagePrimarySpell + damageSecSpell;
    }
}
