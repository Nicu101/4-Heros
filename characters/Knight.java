package characters;

import angels.Angel;
import map.Map;


public class Knight extends Hero {
    private final String typeHero = "K";
    private final String heroType = "Knight";
    private static final int START_HP = 900;
    private final int bonusLevelHp = 80;
    private final int startPrimarySpellDamage = 200;
    private final int bonusLevelPrimarySpellDamage = 30;
    private final double maxLimitPercent = 0.4;
    private final double limitPercent = 0.2;
    private final double bonusLimitPercent = 0.01;
    private final int startSecondarySpellDamage = 100;
    private final int bonusLevelSecondarySpellDamage = 40;
    private final int stunDuration = 1;

    /* Magic Numbers */
    private static final float EXECUTE_KNIGHT_TO_ROGUE_START_MODIFIER = 1.15f;
    private static final float EXECUTE_KNIGHT_TO_PYRO_START_MODIFIER = 1.1f;
    private static final float EXECUTE_KNIGHT_TO_WIZARD_START_MODIFIER = 0.8f;
    private static final float SLAM_KNIGHT_TO_ROGUE_START_MODIFIER = 0.8f;
    private static final float SLAM_KNIGHT_TO_KNIGHT_START_MODIFIER = 1.2f;
    private static final float SLAM_KNIGHT_TO_PYRO_START_MODIFIER = 0.9f;
    private static final float SLAM_KNIGHT_TO_WIZARD_START_MODIFIER = 1.05f;

    /* Modifiers for the spells */
    private float executeKnightToRogueModifier;
    private final float executeKnightToKnightModifier = 1f;
    private float executeKnightToPyroModifier;
    private float executeKnightToWizardModifier;
    private float slamKnightToRogueModifier;
    private float slamKnightToKnightModifier;
    private float slamKnightToPyroModifier;
    private float slamKnightToWizardModifier;

    public Knight(final int coordinateX, final int coordinateY) {
        super(coordinateX, coordinateY, START_HP);
        this.executeKnightToRogueModifier = EXECUTE_KNIGHT_TO_ROGUE_START_MODIFIER;
        this.executeKnightToPyroModifier = EXECUTE_KNIGHT_TO_PYRO_START_MODIFIER;
        this.executeKnightToWizardModifier = EXECUTE_KNIGHT_TO_WIZARD_START_MODIFIER;
        this.slamKnightToRogueModifier = SLAM_KNIGHT_TO_ROGUE_START_MODIFIER;
        this.slamKnightToKnightModifier = SLAM_KNIGHT_TO_KNIGHT_START_MODIFIER;
        this.slamKnightToPyroModifier = SLAM_KNIGHT_TO_PYRO_START_MODIFIER;
        this.slamKnightToWizardModifier = SLAM_KNIGHT_TO_WIZARD_START_MODIFIER;
    }

    public final String getHeroType() {
        return heroType;
    }

    public final float getExecuteKnightToRogueModifier() {
        return executeKnightToRogueModifier;
    }

    public final void setExecuteKnightToRogueModifier(final float executeKnightToRogueModifier) {
        this.executeKnightToRogueModifier = executeKnightToRogueModifier;
    }

    public final float getExecuteKnightToPyroModifier() {
        return executeKnightToPyroModifier;
    }

    public final void setExecuteKnightToPyroModifier(final float executeKnightToPyroModifier) {
        this.executeKnightToPyroModifier = executeKnightToPyroModifier;
    }

    public final float getExecuteKnightToWizardModifier() {
        return executeKnightToWizardModifier;
    }

    public final void setExecuteKnightToWizardModifier(final float executeKnightToWizardModifier) {
        this.executeKnightToWizardModifier = executeKnightToWizardModifier;
    }

    public final float getSlamKnightToRogueModifier() {
        return slamKnightToRogueModifier;
    }

    public final void setSlamKnightToRogueModifier(final float slamKnightToRogueModifier) {
        this.slamKnightToRogueModifier = slamKnightToRogueModifier;
    }

    public final float getSlamKnightToKnightModifier() {
        return slamKnightToKnightModifier;
    }

    public final void setSlamKnightToKnightModifier(final float slamKnightToKnightModifier) {
        this.slamKnightToKnightModifier = slamKnightToKnightModifier;
    }

    public final float getSlamKnightToPyroModifier() {
        return slamKnightToPyroModifier;
    }

    public final void setSlamKnightToPyroModifier(final float slamKnightToPyroModifier) {
        this.slamKnightToPyroModifier = slamKnightToPyroModifier;
    }

    public final float getSlamKnightToWizardModifier() {
        return slamKnightToWizardModifier;
    }

    public final void setSlamKnightToWizardModifier(final float slamKnightToWizardModifier) {
        this.slamKnightToWizardModifier = slamKnightToWizardModifier;
    }

    public final void modifyCoefficients(final float modifiedCoefficient) {
        /* Modify race coefficients for spell EXECUTE */
        this.setExecuteKnightToPyroModifier(this.getExecuteKnightToPyroModifier()
                + modifiedCoefficient);
        this.setExecuteKnightToRogueModifier(this.getExecuteKnightToRogueModifier()
                + modifiedCoefficient);
        this.setExecuteKnightToWizardModifier(this.getExecuteKnightToWizardModifier()
                + modifiedCoefficient);
        /* Modify race coefficients for spell SLAM */
        this.setSlamKnightToPyroModifier(this.getSlamKnightToPyroModifier() + modifiedCoefficient);
        this.setSlamKnightToRogueModifier(this.getSlamKnightToRogueModifier()
                + modifiedCoefficient);
        this.setSlamKnightToWizardModifier(this.getSlamKnightToWizardModifier()
                + modifiedCoefficient);
        this.setSlamKnightToKnightModifier(this.getSlamKnightToKnightModifier()
                + modifiedCoefficient);
    }

    public final String getTypeHero() {
        return typeHero;
    }

    public final void levelUp() {
        /* Level UP*/
        /* Used so that we can keep the bonus hp in the class*/
        super.levelUp(this.bonusLevelHp);
    }

    @Override
    public final int primarySpell(final Hero enemyHero, final Map zone) {
        /* Using Execute*/
        /*
         * damage - damage dealt by the Execute
         * hpLimitForInstaKill - the limit after with the enemy is killed with the Execute
         */
        int damage;
        double hpLimitForInstaKill;

        /* Calculating the execution range/limit */
        hpLimitForInstaKill = Math.max(enemyHero.getMaxHP() * this.maxLimitPercent,
                enemyHero.getMaxHP()
                        * (this.limitPercent + this.bonusLimitPercent * this.getLvl()));
        hpLimitForInstaKill = Math.round(hpLimitForInstaKill);

        /* Checking the enemy current HP to see if he is in executing range*/
        if (enemyHero.getCurrentHP() <= hpLimitForInstaKill) {
            /* Letting the default damage if it can kill the target*/
            damage = this.startPrimarySpellDamage
                    + this.bonusLevelPrimarySpellDamage * this.getLvl();

            if (enemyHero.getCurrentHP() > damage) {
                damage = enemyHero.getCurrentHP();
            }
        } else {
            damage = this.startPrimarySpellDamage
                    + this.bonusLevelPrimarySpellDamage * this.getLvl();
        }

        /* Applying ground modifier*/
        damage = this.acceptGroundModifier(zone, damage);

        return damage;
    }

    @Override
    public final int secondarySpell(final Hero enemyHero, final Map zone) {
        /* Using Slam*/
        /* Imobilizing enemy hero for a turn*/
        enemyHero.setImmobility(true);
        enemyHero.setAffectionDuration(this.stunDuration);
        enemyHero.setAffectionDamage(0);

        /* Calculating the damage done by the spell*/
        int damage = this.startSecondarySpellDamage
                + this.bonusLevelSecondarySpellDamage * this.getLvl();

        /* Applying ground modifier*/
        damage = this.acceptGroundModifier(zone, damage);

        return damage;
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
        /* Calculating damage from Knight to Rogue for Execute*/
        return (int) Math.round((float) damageToModify * this.executeKnightToRogueModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Knight knight,
                                                 final int damageToModify) {
        /* Calculating damage from Knight to Knight for Execute*/
        return (int) Math.round((float) damageToModify * this.executeKnightToKnightModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Pyromancer pyromancer,
                                                 final int damageToModify) {
        /* Calculating damage from Knight to Pyromancer for Execute*/
        return (int) Math.round((float) damageToModify * this.executeKnightToPyroModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Wizard wizard,
                                                 final int damageToModify) {
        /* Calculating damage from Knight to Wizard for Execute*/
        return (int) Math.round((float) damageToModify * this.executeKnightToWizardModifier
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
        /* Calculating damage from Knight to Rogue for Slam*/
        return (int) Math.round((float) damageToModify * this.slamKnightToRogueModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Knight knight,
                                                   final int damageToModify) {
        /* Calculating damage from Knight to Knight for Slam*/
        return (int) Math.round((float) damageToModify * this.slamKnightToKnightModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Pyromancer pyromancer,
                                                   final int damageToModify) {
        /* Calculating damage from Knight to Pyromancer for Slam*/
        return (int) Math.round((float) damageToModify * this.slamKnightToPyroModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Wizard wizard,
                                                   final int damageToModify) {
        /* Calculating damage from Knight to Wizard for Slam*/
        return (int) Math.round((float) damageToModify * this.slamKnightToWizardModifier
                + this.getMagicNumber());
    }

    public final int calculateDamage(final Hero enemyHero, final Map zone) {
        /* Using both abilities with all the modifiers */
        int damagePrimarySpell;
        int damageSecSpell;

        /* Calculating the damage for Execute */
        damagePrimarySpell = this.primarySpell(enemyHero, zone);
        /* Applying race modifiers for Execute*/
        damagePrimarySpell = enemyHero.acceptRaceModifierForPrimarySpell(this,
                damagePrimarySpell);

        /* Calculating the damage for Slam */
        damageSecSpell = this.secondarySpell(enemyHero, zone);
        /* Applying race modifiers for Slam*/
        damageSecSpell = enemyHero.acceptRaceModifierForSecondarySpell(this,
                damageSecSpell);

        /* Returning the damage for both spells*/
        return damagePrimarySpell + damageSecSpell;
    }
}
