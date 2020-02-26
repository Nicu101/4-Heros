package characters;

import angels.Angel;
import map.Map;

public class Pyromancer extends Hero {
    private final String typeHero = "P";
    private final String heroType = "Pyromancer";
    private static final int START_HP = 500;
    private final int bonusLevelHp = 50;
    private final int startPrimarySpellDamage = 350;
    private final int bonusLevelPrimarySpellDamage = 50;
    private final int startSecondarySpellDamage = 150;
    private final int bonusLevelSecondarySpellDamage = 20;
    private final int startDOT = 50;
    private final int bonusDOT = 30;
    private final int durationDOT = 2;

    /* Magic Numbers */
    private static final float PYRO_TO_ROGUE_START_MODIFIER = 0.8f;
    private static final float PYRO_TO_KNIGHT_START_MODIFIER = 1.2f;
    private static final float PYRO_TO_PYRO_START_MODIFIER = 0.9f;
    private static final float PYRO_TO_WIZARD_START_MODIFIER = 1.05f;

    /* Modifiers for the spells */
    private float pyroToRogueModifier;
    private float pyroToKnightModifier;
    private float pyroToPyroModifier;
    private float pyroToWizardModifier;

    public Pyromancer(final int coordinateX, final int coordinateY) {
        super(coordinateX, coordinateY, START_HP);
        this.pyroToKnightModifier = PYRO_TO_KNIGHT_START_MODIFIER;
        this.pyroToPyroModifier = PYRO_TO_PYRO_START_MODIFIER;
        this.pyroToRogueModifier = PYRO_TO_ROGUE_START_MODIFIER;
        this.pyroToWizardModifier = PYRO_TO_WIZARD_START_MODIFIER;
    }

    public final String getHeroType() {
        return heroType;
    }

    public final float getPyroToRogueModifier() {
        return pyroToRogueModifier;
    }

    public final void setPyroToRogueModifier(final float pyroToRogueModifier) {
        this.pyroToRogueModifier = pyroToRogueModifier;
    }

    public final float getPyroToKnightModifier() {
        return pyroToKnightModifier;
    }

    public final void setPyroToKnightModifier(final float pyroToKnightModifier) {
        this.pyroToKnightModifier = pyroToKnightModifier;
    }

    public final float getPyroToPyroModifier() {
        return pyroToPyroModifier;
    }

    public final void setPyroToPyroModifier(final float pyroToPyroModifier) {
        this.pyroToPyroModifier = pyroToPyroModifier;
    }

    public final float getPyroToWizardModifier() {
        return pyroToWizardModifier;
    }

    public final void setPyroToWizardModifier(final float pyroToWizardModifier) {
        this.pyroToWizardModifier = pyroToWizardModifier;
    }

    public final void modifyCoefficients(final float modifiedCoefficient) {
        /* Modify race coefficients for spells */
        this.setPyroToKnightModifier(this.getPyroToKnightModifier() + modifiedCoefficient);
        this.setPyroToPyroModifier(this.getPyroToPyroModifier() + modifiedCoefficient);
        this.setPyroToRogueModifier(this.getPyroToRogueModifier() + modifiedCoefficient);
        this.setPyroToWizardModifier(this.getPyroToWizardModifier() + modifiedCoefficient);
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
        /* Using Fireblast*/
        /* Calculating damage for the spell*/
        int damage = this.startPrimarySpellDamage
                + this.bonusLevelPrimarySpellDamage * this.getLvl();

        /* Applying ground modifier*/
        damage = this.acceptGroundModifier(zone, damage);

        return damage;
    }

    @Override
    public final int secondarySpell(final Hero enemyHero, final Map zone) {
        /* Using Ignite*/
        /* Setting the DOT(damage over time) and duration for Ignite */
        int dot;
        dot = this.startDOT + this.bonusDOT * this.getLvl();
        /* Applying ground modifier for damage over time */
        dot = this.acceptGroundModifier(zone, dot);
        /* Applying race modifier for damage over time */
        dot = enemyHero.acceptRaceModifierForPrimarySpell(this, dot);

        enemyHero.setAffectionDamage(dot);
        enemyHero.setAffectionDuration(this.durationDOT);
        /* The hero can now move */
        enemyHero.setImmobility(false);

        /* Calculating the damage for the spell*/
        int damage = this.startSecondarySpellDamage
                + this.bonusLevelSecondarySpellDamage * this.getLvl();

        /* Applying ground modifier for primary damage */
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
        /* Calculating damage from Pyromancer to Rogue for Fireblast*/
        return (int) Math.round((float) damageToModify * this.pyroToRogueModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Knight knight,
                                                 final int damageToModify) {
        /* Calculating damage from Pyromancer to Knight for Fireblast*/
        return (int) Math.round((float) damageToModify * this.pyroToKnightModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Pyromancer pyromancer,
                                                 final int damageToModify) {
        /* Calculating damage from Pyromancer to Pyromancer for Fireblast*/
        return (int) Math.round((float) damageToModify * this.pyroToPyroModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForPrimarySpell(final Wizard wizard,
                                                 final int damageToModify) {
        /* Calculating damage from Pyromancer to Wizard for Fireblast*/
        return (int) Math.round((float) damageToModify * this.pyroToWizardModifier
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
        /* Calculating damage from Pyromancer to Rogue for Ignite*/
        return (int) Math.round((float) damageToModify * this.pyroToRogueModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Knight knight,
                                                   final int damageToModify) {
        /* Calculating damage from Pyromancer to Knight for Ignite*/
        return (int) Math.round((float) damageToModify * this.pyroToKnightModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Pyromancer pyromancer,
                                                   final int damageToModify) {
        /* Calculating damage from Pyromancer to Pyromancer for Ignite*/
        return (int) Math.round((float) damageToModify * this.pyroToPyroModifier
                + this.getMagicNumber());
    }

    public final int raceModifierForSecondarySpell(final Wizard wizard,
                                                   final int damageToModify) {
        /* Calculating damage from Pyromancer to Wizard for Ignite*/
        return (int) Math.round((float) damageToModify * this.pyroToWizardModifier
                + this.getMagicNumber());
    }

    public final int calculateDamage(final Hero enemyHero, final Map zone) {
        /* Using both abilities with all the modifiers */
        int damagePrimarySpell;
        int damageSecSpell;

        /* Calculating the damage for Fireblast */
        damagePrimarySpell = this.primarySpell(enemyHero, zone);
        /* Applying race modifiers for Fireblast*/
        damagePrimarySpell = enemyHero.acceptRaceModifierForPrimarySpell(this,
                damagePrimarySpell);

        /* Calculating the damage for Ignite */
        damageSecSpell = this.secondarySpell(enemyHero, zone);
        /* Applying race modifiers for Fireblast*/
        damageSecSpell = enemyHero.acceptRaceModifierForSecondarySpell(this,
                damageSecSpell);

        /* Returning the damage for both spells*/
        return damagePrimarySpell + damageSecSpell;
    }
}
