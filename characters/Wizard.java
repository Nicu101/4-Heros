package characters;

import angels.Angel;
import map.Map;

public class Wizard extends Hero {
    private final String typeHero = "W";
    private final String heroType = "Wizard";
    private static final int START_HP = 400;
    private final int bonusLevelHp = 30;
    private final int startPrimarySpellPercent = 20;
    private final int bonusLevelPrimarySpellPercent = 5;
    private final float baseHpPercent = 0.3f;
    private final int startSecondarySpellPercent = 35;
    private final int bonusLevelSecondarySpellPercent = 2;
    private final int maxSecondarySpellPercent = 70;
    private final int transformToPercent = 100;

    /* Magic Numbers */
    private static final float DRAIN_WIZARD_TO_ROGUE_START_MODIFIER = 0.8f;
    private static final float DRAIN_WIZARD_TO_KNIGHT_START_MODIFIER = 1.2f;
    private static final float DRAIN_WIZARD_TO_PYRO_START_MODIFIER = 0.9f;
    private static final float DRAIN_WIZARD_TO_WIZARD_START_MODIFIER = 1.05f;
    private static final float DEFLECT_WIZARD_TO_ROGUE_START_MODIFIER = 1.2f;
    private static final float DEFLECT_WIZARD_TO_KNIGHT_START_MODIFIER = 1.4f;
    private static final float DEFLECT_WIZARD_TO_PYRO_START_MODIFIER = 1.3f;

    /* Modifiers for the spells */
    private float drainWizardToRogueModifier;
    private float drainWizardToKnightModifier;
    private float drainWizardToPyroModifier;
    private float drainWizardToWizardModifier;
    private float deflectWizardToRogueModifier;
    private float deflectWizardToKnightModifier;
    private float deflectWizardToPyroModifier;
    private final float deflectWizardToWizardModifier = 0;

    public Wizard(final int coordinateX, final int coordinateY) {
        super(coordinateX, coordinateY, START_HP);
        this.drainWizardToRogueModifier = DRAIN_WIZARD_TO_ROGUE_START_MODIFIER;
        this.drainWizardToKnightModifier = DRAIN_WIZARD_TO_KNIGHT_START_MODIFIER;
        this.drainWizardToPyroModifier = DRAIN_WIZARD_TO_PYRO_START_MODIFIER;
        this.drainWizardToWizardModifier = DRAIN_WIZARD_TO_WIZARD_START_MODIFIER;
        this.deflectWizardToRogueModifier = DEFLECT_WIZARD_TO_ROGUE_START_MODIFIER;
        this.deflectWizardToKnightModifier = DEFLECT_WIZARD_TO_KNIGHT_START_MODIFIER;
        this.deflectWizardToPyroModifier = DEFLECT_WIZARD_TO_PYRO_START_MODIFIER;
    }

    public final String getHeroType() {
        return heroType;
    }

    public final float getDrainWizardToRogueModifier() {
        return drainWizardToRogueModifier;
    }

    public final void setDrainWizardToRogueModifier(final float drainWizardToRogueModifier) {
        this.drainWizardToRogueModifier = drainWizardToRogueModifier;
    }

    public final float getDrainWizardToKnightModifier() {
        return drainWizardToKnightModifier;
    }

    public final void setDrainWizardToKnightModifier(final float drainWizardToKnightModifier) {
        this.drainWizardToKnightModifier = drainWizardToKnightModifier;
    }

    public final float getDrainWizardToPyroModifier() {
        return drainWizardToPyroModifier;
    }

    public final void setDrainWizardToPyroModifier(final float drainWizardToPyroModifier) {
        this.drainWizardToPyroModifier = drainWizardToPyroModifier;
    }

    public final float getDrainWizardToWizardModifier() {
        return drainWizardToWizardModifier;
    }

    public final void setDrainWizardToWizardModifier(final float drainWizardToWizardModifier) {
        this.drainWizardToWizardModifier = drainWizardToWizardModifier;
    }

    public final float getDeflectWizardToRogueModifier() {
        return deflectWizardToRogueModifier;
    }

    public final void setDeflectWizardToRogueModifier(final float deflectWizardToRogueModifier) {
        this.deflectWizardToRogueModifier = deflectWizardToRogueModifier;
    }

    public final float getDeflectWizardToKnightModifier() {
        return deflectWizardToKnightModifier;
    }

    public final void setDeflectWizardToKnightModifier(final float deflectWizardToKnightModifier) {
        this.deflectWizardToKnightModifier = deflectWizardToKnightModifier;
    }

    public final float getDeflectWizardToPyroModifier() {
        return deflectWizardToPyroModifier;
    }

    public final void setDeflectWizardToPyroModifier(final float deflectWizardToPyroModifier) {
        this.deflectWizardToPyroModifier = deflectWizardToPyroModifier;
    }

    public final void modifyCoefficients(final float modifiedCoefficient) {
        /* Modify race coefficients for spell DRAIN */
        this.setDrainWizardToKnightModifier(this.getDrainWizardToKnightModifier()
                + modifiedCoefficient);
        this.setDrainWizardToRogueModifier(this.getDrainWizardToRogueModifier()
                + modifiedCoefficient);
        this.setDrainWizardToPyroModifier(this.getDrainWizardToPyroModifier()
                + modifiedCoefficient);
        this.setDrainWizardToWizardModifier(this.getDrainWizardToWizardModifier()
                + modifiedCoefficient);
        /* Modify race coefficients for spell DEFLECT */
        this.setDeflectWizardToKnightModifier(this.getDeflectWizardToKnightModifier()
                + modifiedCoefficient);
        this.setDeflectWizardToRogueModifier(this.getDeflectWizardToRogueModifier()
                + modifiedCoefficient);
        this.setDeflectWizardToPyroModifier(this.getDeflectWizardToPyroModifier()
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
        /* Using DRAIN */
        /* percent - the percent of damage that DRAIN deals */
        float percent = this.startPrimarySpellPercent
                + this.bonusLevelPrimarySpellPercent * this.getLvl();

        /* Calculate the hp that the drain spell affects */
        float baseHp;
        baseHp = Math.min(this.baseHpPercent * enemyHero.getMaxHP(),
                enemyHero.getCurrentHP());

        /* Damage dealt by Drain*/
        int damage;
        damage = (int) Math.round(percent * baseHp
                + this.getMagicNumber());

        /* Applying ground modifier*/
        damage = this.acceptGroundModifier(zone, damage);

        return damage;
    }

    public final int secondarySpell(final Hero enemyHero, final Map zone) {
        /* Using DEFLECT*/

        /* This spell has no effect on wizards */
        if (enemyHero.getTypeHero() == this.getTypeHero()) {
            return 0;
        }

        float percent;
        /* Calculate the percent of damage that will be reflected */
        percent = Math.min(this.startSecondarySpellPercent
                        + this.bonusLevelSecondarySpellPercent * this.getLvl(),
                this.maxSecondarySpellPercent);

        /* Received damage from the enemy */
        int recevedDamage;
        recevedDamage = enemyHero.primarySpell(this, zone)
                + enemyHero.secondarySpell(this, zone);

        /* Calculate damage deflected */
        int damage;
        damage = (int) Math.round(percent * (float) recevedDamage
                + this.getMagicNumber());

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
        /* Using double dispatch so that+*/
        /* this = enemy hero type */
        /* attackingHero = the hero that is launching spells (attacking) */
        return attackingHero.raceModifierForPrimarySpell(this,
                damagePrimarySpell);
    }

    public final int raceModifierForPrimarySpell(final Rogue rogue,
                                                 final int damageToModify) {
        /* Calculating damage from Pyromancer to Rogue for Drain*/
        return (int) Math.round((double) damageToModify * this.drainWizardToRogueModifier
                / this.transformToPercent
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Knight knight,
                                                 final int damageToModify) {
        /* Calculating damage from Pyromancer to Knight for Drain*/
        return (int) Math.round((double) damageToModify * this.drainWizardToKnightModifier
                / this.transformToPercent
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Pyromancer pyromancer,
                                                 final int damageToModify) {
        /* Calculating damage from Pyromancer to Pyromancer for Drain*/
        return (int) Math.round((double) damageToModify * this.drainWizardToPyroModifier
                / this.transformToPercent
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Wizard wizard,
                                                 final int damageToModify) {
        /* Calculating damage from Pyromancer to Wizard for Drain*/
        return (int) Math.round((double) damageToModify * this.drainWizardToWizardModifier
                / this.transformToPercent
                + this.getMagicNumber());
    }

    public final int acceptRaceModifierForSecondarySpell(final Hero attackingHero,
                                                         final int damagePrimarySpell) {
        /* Using double dispatch so that this is transmitting enemy type*/
        /* this = enemy hero type */
        /* attackingHero = the hero that is launching spells (attacking) */
        return attackingHero.raceModifierForSecondarySpell(this,
                damagePrimarySpell);
    }

    public final int raceModifierForSecondarySpell(final Rogue rogue,
                                                   final int damageToModify) {
        /* Calculating damage from Pyromancer to Rogue for Deflect*/
        return (int) Math.round((double) damageToModify * this.deflectWizardToRogueModifier
                / this.transformToPercent
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Knight knight,
                                                   final int damageToModify) {
        /* Calculating damage from Pyromancer to Knight for Deflect*/
        return (int) Math.round((double) damageToModify * this.deflectWizardToKnightModifier
                / this.transformToPercent
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Pyromancer pyromancer,
                                                   final int damageToModify) {
        /* Calculating damage from Pyromancer to Pyromancer for Deflect*/
        return (int) Math.round((double) damageToModify * this.deflectWizardToPyroModifier
                / this.transformToPercent
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Wizard wizard,
                                                   final int damageToModify) {
        /* Calculating damage from Pyromancer to Wizard for Deflect*/
        return (int) Math.round((double) damageToModify * this.deflectWizardToWizardModifier
                / this.transformToPercent
                + this.getMagicNumber());
    }

    @Override
    public final int calculateDamage(final Hero enemyHero, final Map zone) {
        /* Using both abilities with all the modifiers */

        /* Calculating the damage for Drain */
        int damagePrimarySpell;
        damagePrimarySpell = this.primarySpell(enemyHero, zone);
        /* Applying race modifiers */
        damagePrimarySpell = enemyHero.acceptRaceModifierForPrimarySpell(this,
                damagePrimarySpell);

        /* Calculating the damage for Deflect */
        int damageSecondarySpell;
        damageSecondarySpell = this.secondarySpell(enemyHero, zone);
        /* Applying race modifiers */
        damageSecondarySpell = enemyHero.acceptRaceModifierForSecondarySpell(this,
                damageSecondarySpell);

        /* Returning the damage for both spells*/
        return damagePrimarySpell + damageSecondarySpell;
    }
}
